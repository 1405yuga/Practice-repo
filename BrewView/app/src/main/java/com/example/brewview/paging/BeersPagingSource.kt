package com.example.brewview.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.brewview.model.BeersResult
import com.example.brewview.model.BeersResultItem
import com.example.brewview.network.BeersApi

class BeersPagingSource :
    PagingSource<Int, BeersResultItem>() {

    override fun getRefreshKey(state: PagingState<Int, BeersResultItem>): Int? {
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

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, BeersResultItem> {
        try {
            val position: Int = params.key ?: 1
            val response =
                BeersApi.retrofitApiService.getBeersList(position, ProjectConstants.PER_PAGE)
            val data : BeersResult = response
            if(position % ProjectConstants.FULL_WIDTH_POSITION == 0){
                // TODO: add ad item 
            }
            return LoadResult.Page(
                data = response,
                prevKey = if (position == 1) null else position.minus(1),
                nextKey = if (position == 325) null else position.plus(1)
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }
}