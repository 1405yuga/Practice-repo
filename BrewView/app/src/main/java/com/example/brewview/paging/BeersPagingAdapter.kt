package com.example.brewview.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.brewview.R
import com.example.brewview.databinding.LayoutItemBeerBinding
import com.example.brewview.model.BeersResult
import com.example.brewview.model.BeersResultItem

class BeersPagingAdapter : PagingDataAdapter<BeersResultItem,BeersPagingAdapter.BeerViewHolder>(DiffCallBack) {
    object DiffCallBack : DiffUtil.ItemCallback<BeersResultItem>() {
        override fun areItemsTheSame(oldItem: BeersResultItem, newItem: BeersResultItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: BeersResultItem,
            newItem: BeersResultItem
        ): Boolean {
            return oldItem == newItem
        }

    }

    class BeerViewHolder(val binding: LayoutItemBeerBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(beer : BeersResultItem){
            binding.apply {
                name.text = beer.name
                description.text = beer.description
                image.load(beer.image_url){
                    placeholder(R.drawable.loading_animation)
                }
            }
        }
    }

    override fun onBindViewHolder(holder: BeerViewHolder, position: Int) {
        if(getItem(position)!=null){
            holder.bind(getItem(position)!!)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeerViewHolder {
        return BeerViewHolder(LayoutItemBeerBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }
}