package com.example.emojis.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.emojis.databinding.FragmentSmileyEmojisBinding

private const val TAG = "SmileyEmojisFragment tag"

class SmileyEmojisFragment : Fragment() {

    private lateinit var binding: FragmentSmileyEmojisBinding
    private lateinit var viewModel: EmojisViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSmileyEmojisBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(EmojisViewModel::class.java)
        viewModel.getSmileyEmojisList()

        viewModel.smileyEmojisList.observe(viewLifecycleOwner, Observer {
            for (emoji in it) {
                Log.d(TAG, "EMOJI : $emoji")
            }
        })
        return binding.root
    }

}