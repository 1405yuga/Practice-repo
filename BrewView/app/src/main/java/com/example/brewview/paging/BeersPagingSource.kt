package com.example.brewview.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.brewview.model.BeersResultItem
import com.example.brewview.network.BeersApiService

class BeersPagingSource(val beersApiService: BeersApiService) : PagingSource<Int,BeersResultItem>() {
    override fun getRefreshKey(state: PagingState<Int, BeersResultItem>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, BeersResultItem> {
        TODO("Not yet implemented")
    }
}