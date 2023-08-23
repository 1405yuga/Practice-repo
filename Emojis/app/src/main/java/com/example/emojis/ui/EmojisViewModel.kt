package com.example.emojis.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.emojis.network.Emoji

class EmojisViewModel : ViewModel(){

    //list to store smiley emojis
    private  val _smileyEmojisList = MutableLiveData<List<Emoji>>()
    val smileyEmojisList : LiveData<List<Emoji>> = _smileyEmojisList
}