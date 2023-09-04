package com.example.brewview

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.brewview.databinding.ActivityMainBinding
import com.example.brewview.paging.BeersPagingAdapter
import com.example.brewview.paging.ProjectConstants
import com.example.brewview.viewmodel.BeerViewModel

private const val TAG = "MainActivity tag"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: BeerViewModel
    private lateinit var beerAdapter: BeersPagingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        beerAdapter = BeersPagingAdapter()
        viewModel = ViewModelProvider(this).get(BeerViewModel::class.java)

        val gridLayoutManager = GridLayoutManager(this@MainActivity,2)

        // to convert every 5th element for full width
        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup(){
            override fun getSpanSize(position: Int): Int {
                when(beerAdapter.getItemViewType(position)){
                    ProjectConstants.AD_VIEW_TYPE -> return 2
                    else -> return 1
                }
            }

        }

        binding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = gridLayoutManager
            adapter = beerAdapter
        }

        viewModel.beersList.observe(this, Observer {
            beerAdapter.submitData(lifecycle, it)
            Log.d(TAG, "Set list")
        })
    }
}