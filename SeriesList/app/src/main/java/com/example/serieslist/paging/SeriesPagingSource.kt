package com.example.serieslist.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.serieslist.model.Result
import com.example.serieslist.network.SeriesApiService

class SeriesPagingSource(val seriesApi: SeriesApiService) : PagingSource<Int, Result>() {
    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult.Page<Int, Result> {

    }
}