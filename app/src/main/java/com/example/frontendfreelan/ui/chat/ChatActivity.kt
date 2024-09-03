package com.example.frontendfreelan.ui.chat

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.frontendfreelan.R

class ChatActivity : AppCompatActivity() {

    private val chatViewModel: ChatViewModel by viewModels()
    private lateinit var adapter: MessageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        val recyclerView: RecyclerView = findViewById(R.id.chat_recycler_view)
        val closeButton: Button = findViewById(R.id.close_button)
        val anotherButton: Button = findViewById(R.id.another_button)
        val sendButton: Button = findViewById(R.id.send_button)
        val messageInput: EditText = findViewById(R.id.message_input)

        adapter = MessageAdapter(chatViewModel.messages.value ?: listOf())
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        chatViewModel.messages.observe(this) { messages ->
            adapter.updateMessages(messages)
            recyclerView.scrollToPosition(adapter.itemCount - 1)
        }

        closeButton.setOnClickListener {
            // Ação para fechar a atividade ou outra funcionalidade
            finish()
        }

        anotherButton.setOnClickListener {
            // Ação para o outro botão
        }

        sendButton.setOnClickListener {
            val messageContent = messageInput.text.toString()
            if (messageContent.isNotBlank()) {
                chatViewModel.sendMessage("User", messageContent)
                messageInput.text.clear()
            }
        }
    }
}
