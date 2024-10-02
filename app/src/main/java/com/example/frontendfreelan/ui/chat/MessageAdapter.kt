package com.example.frontendfreelan.ui.chat

import android.view.LayoutInflater // Importa a classe LayoutInflater para inflar layouts
import android.view.View // Importa a classe View para representar a interface do usuário
import android.view.ViewGroup // Importa a classe ViewGroup para representar o contêiner de views
import android.widget.TextView // Importa a classe TextView para exibir texto
import androidx.recyclerview.widget.RecyclerView // Importa a classe RecyclerView para exibir listas de dados
import com.example.frontendfreelan.R // Importa o recurso R para acessar recursos do projeto
import com.example.frontendfreelan.ui.chat.ChatViewModel.Message // Importa a classe Message do ViewModel
import java.text.SimpleDateFormat // Importa a classe SimpleDateFormat para formatar datas
import java.util.Date // Importa a classe Date para representar datas
import java.util.Locale // Importa a classe Locale para representar configurações regionais

// Define o adaptador para o RecyclerView que exibe as mensagens do chat
class MessageAdapter(private var messages: List<Message>) : RecyclerView.Adapter<MessageAdapter.ViewHolder>() {

    // Define o ViewHolder que contém as views para exibir uma mensagem
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val senderView: TextView = view.findViewById(R.id.message_sender) // TextView para exibir o remetente da mensagem
        val contentView: TextView = view.findViewById(R.id.message_content) // TextView para exibir o conteúdo da mensagem
        val timestampView: TextView = view.findViewById(R.id.message_timestamp) // TextView para exibir o timestamp da mensagem
    }

    // Cria novas views (invocada pelo layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context) // Obtém o LayoutInflater do contexto do pai
            .inflate(R.layout.item_message, parent, false) // Infla o layout do item de mensagem
        return ViewHolder(view) // Retorna uma nova instância do ViewHolder
    }

    // Substitui o conteúdo de uma view (invocada pelo layout manager)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val message = messages[position] // Obtém a mensagem na posição especificada
        holder.senderView.text = message.sender // Define o texto do remetente na TextView correspondente
        holder.contentView.text = message.content // Define o texto do conteúdo na TextView correspondente
        holder.timestampView.text = SimpleDateFormat("HH:mm", Locale.getDefault()) // Cria um formatador de data
            .format(Date(message.timestamp)) // Formata o timestamp da mensagem e define na TextView correspondente
    }

    // Retorna o tamanho do dataset (invocada pelo layout manager)
    override fun getItemCount(): Int = messages.size // Retorna o número de mensagens na lista

    // Atualiza a lista de mensagens e notifica o adaptador para atualizar a exibição
    fun updateMessages(newMessages: List<Message>) {
        messages = newMessages // Atualiza a lista de mensagens
        notifyDataSetChanged() // Notifica o adaptador que os dados mudaram
    }
}