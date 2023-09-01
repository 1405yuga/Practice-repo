package com.example.brewview.paging

import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.brewview.databinding.LayoutItemBeerBinding
import com.example.brewview.model.BeersResult
import com.example.brewview.model.BeersResultItem

class BeersPagingAdapter : PagingDataAdapter<BeersResultItem,BeersPagingAdapter.BeerViewHolder>() {

    class BeerViewHolder(val binding: LayoutItemBeerBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(beer : BeersResultItem){
            binding.apply {
                name.text = beer.name
                description.text = beer.description
                image.load(beer.image_url)
            }
        }
    }
}