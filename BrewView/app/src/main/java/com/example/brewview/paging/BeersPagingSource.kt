package com.example.brewview.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.brewview.model.BeersResultItem
import com.example.brewview.network.BeersApiService

class BeersPagingSource(val beersApiService: BeersApiService) :
    PagingSource<Int, BeersResultItem>() {
    override fun getRefreshKey(state: PagingState<Int, BeersResultItem>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, BeersResultItem> {
        try {
            val position: Int = params.key ?: 1
            val response = beersApiService.getBeersList(position, ProjectConstants.PER_PAGE)
            return LoadResult.Page(
                data = response,
                prevKey = if (position == 1) null else position.minus(1),
                nextKey = if(position==325) null else position.plus(1))
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }
}