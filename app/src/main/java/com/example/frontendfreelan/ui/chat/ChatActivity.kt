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

    // Declaração do ViewModel para gerenciar os dados do chat
    private val chatViewModel: ChatViewModel by viewModels()
    // Declaração do adaptador para o RecyclerView
    private lateinit var adapter: MessageAdapter

    // Função chamada quando a atividade é criada
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        // Inicializa os componentes da interface do usuário
        val recyclerView: RecyclerView = findViewById(R.id.chat_recycler_view)
        val closeButton: Button = findViewById(R.id.close_button)
        val anotherButton: Button = findViewById(R.id.another_button)
        val sendButton: Button = findViewById(R.id.send_button)
        val messageInput: EditText = findViewById(R.id.message_input)

        // Configura o adaptador e o layout do RecyclerView
        adapter = MessageAdapter(chatViewModel.messages.value ?: listOf())
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Observa as mudanças na lista de mensagens e atualiza o RecyclerView
        chatViewModel.messages.observe(this) { messages ->
            adapter.updateMessages(messages)
            recyclerView.scrollToPosition(adapter.itemCount - 1)
        }

        // Define a ação do botão de fechar
        closeButton.setOnClickListener {
            // Ação para fechar a atividade ou outra funcionalidade
            finish()
        }

        // Define a ação do outro botão
        anotherButton.setOnClickListener {
            // Ação para o outro botão
        }

        // Define a ação do botão de enviar mensagem
        sendButton.setOnClickListener {
            val messageContent = messageInput.text.toString()
            if (messageContent.isNotBlank()) {
                // Envia a mensagem através do ViewModel
                chatViewModel.sendMessage("User", messageContent)
                // Limpa o campo de entrada de mensagem
                messageInput.text.clear()
            }
        }
    }
}
