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
                    setEmojiGroupName(EmojiGroupNames.SMILEYS_EMOJIS)
                }
                R.id.people_body -> {
                    setEmojiGroupName(EmojiGroupNames.PEOPLE_BODY)
                }
                R.id.food_driks -> {
                    setEmojiGroupName(EmojiGroupNames.FOOD_DRINK)
                }
                R.id.components -> {
                    setEmojiGroupName(EmojiGroupNames.COMPONENT)
                }
                R.id.travel_places -> {
                    setEmojiGroupName(EmojiGroupNames.TRAVEL_PLACES)
                }
                R.id.animal_nature -> {
                    setEmojiGroupName(EmojiGroupNames.ANIMALS_NATURE)
                }
                else -> false
            }

        }
        return binding.root
    }

    private fun setEmojiGroupName(smileysEmojis: String) : Boolean{
        viewModel.getSmileyEmojisList(smileysEmojis)
        return true
    }

}