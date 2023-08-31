package com.example.serieslist.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.serieslist.model.Result
import com.example.serieslist.network.SeriesApiService

class SeriesPagingSource(val seriesApi: SeriesApiService) : PagingSource<Int, Result>() {

    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {

    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        try {

            //get pageNo
            val position = params.key ?: 1
            //load Result
            val response = seriesApi.getCharacters(position)
            //create a page
            return LoadResult.Page(
                data = response.results,
                prevKey = if (position == 1) null else position.minus(1),
                nextKey = if (position == response.info.pages) null else position.plus(1)
            )

        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }
}