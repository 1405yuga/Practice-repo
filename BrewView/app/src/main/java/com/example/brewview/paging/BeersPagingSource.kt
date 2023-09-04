package com.example.brewview.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.brewview.model.AdMobItem
import com.example.brewview.network.BeersApi


private const val TAG = "BeersPagingSource tag"

class BeersPagingSource :
    PagingSource<Int, Any>() {

    override fun getRefreshKey(state: PagingState<Int, Any>): Int? {
        val anchorPosition = state.anchorPosition
        if (anchorPosition != null) {
            val anchorPage = state.closestPageToPosition(anchorPosition)
            if (anchorPage?.prevKey != null) {
                return anchorPage.prevKey?.plus(1)
            } else if (anchorPage?.nextKey != null) {
                return anchorPage.nextKey?.minus(1)
            }
        }
        return null

    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Any> {
        try {

            val position: Int = params.key ?: 1
            Log.d(TAG, "current page : $position")
            val responses =
                BeersApi.retrofitApiService.getBeersList(position, ProjectConstants.PER_PAGE)
            val data = mutableListOf<Any>()

            for (index in responses.indices) {
                if (index % ProjectConstants.FULL_WIDTH_POSITION == 0) {
                    data.add(AdMobItem(position.toString()))
                }
                data.add(responses.get(index))
            }

            Log.d(TAG, "Response ${responses.size}")
            /*
            if(position % ProjectConstants.FULL_WIDTH_POSITION == 0){
                data.add(AdMobItem(Resources.getSystem().getString(R.string.ad_mob_id)))
            }

             */
            Log.d(TAG, "DATA ${data.size}")
            return LoadResult.Page(
                data = data,
                prevKey = if (position == 1) null else position.minus(1),
                nextKey = if (position == 325) null else position.plus(1)
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

}