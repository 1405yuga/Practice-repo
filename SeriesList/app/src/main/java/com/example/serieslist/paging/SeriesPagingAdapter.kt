package com.example.serieslist.paging

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.example.serieslist.databinding.CharacterItemBinding
import com.example.serieslist.model.Result

class SeriesPagingAdapter : PagingDataAdapter<Result,SeriesPagingAdapter.SeriesPagingViewHolder>(DiffCallBack) {
    object DiffCallBack : DiffUtil.ItemCallback<Result>() {
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem == newItem
        }

    }

    class SeriesPagingViewHolder(private val binding : CharacterItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(result: Result?){
            binding.apply {
                name.text = result?.name ?: "Not found"
                gender.text = result?.gender ?: "Not found"
                if (result != null) {
                    image.load(result.url)
                }
            }
        }
    }

    override fun onBindViewHolder(holder: SeriesPagingViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeriesPagingViewHolder {
        return SeriesPagingViewHolder(CharacterItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }
}