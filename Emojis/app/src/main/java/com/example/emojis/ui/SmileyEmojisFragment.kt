package com.example.emojis.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.emojis.R
import com.example.emojis.databinding.FragmentSmileyEmojisBinding


class SmileyEmojisFragment : Fragment() {

    private lateinit var binding: FragmentSmileyEmojisBinding
    private lateinit var viewModel: EmojisViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSmileyEmojisBinding.inflate(inflater,container,false)
        viewModel = ViewModelProvider(requireActivity()).get(EmojisViewModel::class.java)

        binding.getBtn.setOnClickListener {
            viewModel.getSmileyEmojisList()
        }
        viewModel.smileyEmojisList.observe(viewLifecycleOwner, Observer {
            binding.result.text = it.size.toString()
        })
        return binding.root
    }

}