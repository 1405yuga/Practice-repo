package com.example.serieslist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.serieslist.network.SeriesApi
import com.example.serieslist.network.SeriesApiService
import com.example.serieslist.paging.SeriesPagingSource

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Pager(
            config = PagingConfig(pageSize = 20, maxSize = 50),
            pagingSourceFactory = { SeriesPagingSource(seriesApi = SeriesApi.retrofitApiService) }
        ).liveData.observe(this){

        }
    }
}