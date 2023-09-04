package com.example.brewview.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.brewview.R
import com.example.brewview.databinding.ItemAdmobBinding
import com.example.brewview.databinding.LayoutItemBeerBinding
import com.example.brewview.model.AdMobItem
import com.example.brewview.model.BeersResultItem
import com.google.android.gms.ads.AdRequest

class BeersPagingAdapter : PagingDataAdapter<Any, RecyclerView.ViewHolder>(DiffCallBack) {
    object DiffCallBack : DiffUtil.ItemCallback<Any>() {
        override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
            return oldItem.equals(newItem)
        }

        override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
            return false
        }

    }

    class BeerViewHolder(val binding: LayoutItemBeerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(beer: BeersResultItem) {
            binding.apply {
                name.text = beer.name
                description.text = beer.description
                image.load(beer.image_url) {
                    placeholder(R.drawable.loading_animation)
                }
            }
        }
    }

    class AdViewHolder(val binding: ItemAdmobBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(adItem: AdMobItem) {
            val adRequest = AdRequest.Builder().build()
            binding.adView.apply {
                adUnitId = adItem.adUnitIdString
                loadAd(adRequest)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return when (item) {
            is AdMobItem -> ProjectConstants.AD_VIEW_TYPE
            is BeersResultItem -> ProjectConstants.BEER_VIEW_TYPE
            else -> throw IllegalArgumentException("Incorrect item ")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is AdViewHolder ->
                if (getItem(position) != null) {
                    holder.bind(getItem(position)!! as AdMobItem)
                }

            is BeerViewHolder -> if (getItem(position) != null) {
                holder.bind(getItem(position)!! as BeersResultItem)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ProjectConstants.AD_VIEW_TYPE -> AdViewHolder(
                ItemAdmobBinding.inflate(
                    LayoutInflater.from(
                        parent.context
                    ), parent, false
                )
            )

            else -> BeerViewHolder(
                LayoutItemBeerBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }


}

