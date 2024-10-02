package com.example.frontendfreelan.ui.chat

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ChatViewModel : ViewModel() {

    // Classe de dados para representar uma mensagem
    data class Message(val sender: String, val content: String, val timestamp: Long)

    // LiveData privada para armazenar a lista de mensagens
    private val _messages = MutableLiveData<List<Message>>().apply {
        value = listOf() // Inicializa a lista de mensagens vazia
    }
    // LiveData pública para expor a lista de mensagens
    val messages: LiveData<List<Message>> = _messages

    // Função para enviar uma nova mensagem
    fun sendMessage(sender: String, content: String) {
        // Cria uma nova mensagem com o remetente, conteúdo e timestamp atual
        val newMessage = Message(sender, content, System.currentTimeMillis())
        // Adiciona a nova mensagem à lista de mensagens existente
        _messages.value = _messages.value?.plus(newMessage)
    }
}
