package com.example.emojis.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.emojis.R
import com.example.emojis.adapter.EmojiAdapter
import com.example.emojis.databinding.FragmentSmileyEmojisBinding
import com.example.emojis.network.EmojiGroupNames

private const val TAG = "SmileyEmojisFragment tag"

class SmileyEmojisFragment : Fragment() {

    private lateinit var binding: FragmentSmileyEmojisBinding
    private lateinit var viewModel: EmojisViewModel
    private lateinit var emojiAdapter: EmojiAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSmileyEmojisBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(EmojisViewModel::class.java)
        emojiAdapter = EmojiAdapter()

        viewModel.getSmileyEmojisList(EmojiGroupNames.SMILEYS_EMOJIS)

        viewModel.smileyEmojisList.observe(viewLifecycleOwner, Observer {
            emojiAdapter.submitList(it)
        })

        binding.resultRecyclerView.adapter = emojiAdapter
        binding.topAppBar.setOnMenuItemClickListener { menuItem ->
            menuItem.isChecked = true
            when (menuItem.itemId) {
                R.id.smiley_emotions -> {
                    viewModel.getSmileyEmojisList(EmojiGroupNames.SMILEYS_EMOJIS)
                    true
                }

                R.id.people_body -> {
                    viewModel.getSmileyEmojisList(EmojiGroupNames.PEOPLE_BODY)
                    true
                }
                else -> false
            }

        }
        return binding.root
    }

}