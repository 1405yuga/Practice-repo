package com.example.serieslist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.serieslist.databinding.ActivityMainBinding
import com.example.serieslist.network.SeriesApi
import com.example.serieslist.network.SeriesApiService
import com.example.serieslist.paging.SeriesPagingAdapter
import com.example.serieslist.paging.SeriesPagingSource

private const val TAG = "MainActivity tag"

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var seriesPagingAdapter: SeriesPagingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        seriesPagingAdapter = SeriesPagingAdapter()
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = seriesPagingAdapter

        }

        Pager(
            config = PagingConfig(pageSize = 20, maxSize = 100),
            pagingSourceFactory = { SeriesPagingSource(seriesApi = SeriesApi.retrofitApiService) }
        ).liveData.cachedIn(this.lifecycleScope).observe(this){
            seriesPagingAdapter.submitData(lifecycle,it)
            Log.d(TAG,"list observer called")
        }
    }
}