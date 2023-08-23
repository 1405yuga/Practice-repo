package com.example.emojis.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.emojis.network.Emoji
import com.example.emojis.network.EmojiApi
import kotlinx.coroutines.launch

private const val TAG = "EmojisViewModel tag"

class EmojisViewModel : ViewModel() {

    //list to store smiley emojis
    private val _smileyEmojisList = MutableLiveData<List<Emoji>>()
    val smileyEmojisList: LiveData<List<Emoji>> = _smileyEmojisList

    fun getSmileyEmojisList(emojisGroup: String) {
        viewModelScope.launch {
            try {
                _smileyEmojisList.value = EmojiApi.retrofitServiceApi.getSmileysEmotion(emojisGroup)
                Log.d(TAG, "List received : ${smileyEmojisList.value?.size}")
            } catch (e: Exception) {
                Log.d(TAG, "ERROR FOUND : ${e.message}")
            }

        }
    }
}