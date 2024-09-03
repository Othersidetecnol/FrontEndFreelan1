package com.example.frontendfreelan.ui.chat

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ChatViewModel : ViewModel() {

    data class Message(val sender: String, val content: String, val timestamp: Long)

    private val _messages = MutableLiveData<List<Message>>().apply {
        value = listOf()
    }
    val messages: LiveData<List<Message>> = _messages

    fun sendMessage(sender: String, content: String) {
        val newMessage = Message(sender, content, System.currentTimeMillis())
        _messages.value = _messages.value?.plus(newMessage)
    }
}
