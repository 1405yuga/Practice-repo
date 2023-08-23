package com.example.emojis.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.emojis.R
import com.example.emojis.databinding.EmojiItemBinding
import com.example.emojis.network.Emoji

class EmojiAdapter() : ListAdapter<Emoji, EmojiAdapter.EmojiViewHolder>(DiffCallBack) {
    companion object {
        private val DiffCallBack = object : DiffUtil.ItemCallback<Emoji>(){
            override fun areItemsTheSame(oldItem: Emoji, newItem: Emoji): Boolean {
                return oldItem.code == newItem.code
            }

            override fun areContentsTheSame(oldItem: Emoji, newItem: Emoji): Boolean {
                return oldItem == newItem
            }

        }
    }

    class EmojiViewHolder(private val binding: EmojiItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(emoji: Emoji){
            binding.emojiName.text = emoji.name
            binding.emojiImage.load(emoji.image){
                placeholder(R.drawable.loading_animation)
                error(R.drawable.baseline_error_outline_24)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmojiViewHolder {
        return EmojiViewHolder(EmojiItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: EmojiViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}