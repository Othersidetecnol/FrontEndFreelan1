package com.example.frontendfreelan.ui.chat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.frontendfreelan.R
import com.example.frontendfreelan.ui.chat.ChatViewModel.Message
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MessageAdapter(private var messages: List<Message>) : RecyclerView.Adapter<MessageAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val senderView: TextView = view.findViewById(R.id.message_sender)
        val contentView: TextView = view.findViewById(R.id.message_content)
        val timestampView: TextView = view.findViewById(R.id.message_timestamp)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_message, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val message = messages[position]
        holder.senderView.text = message.sender
        holder.contentView.text = message.content
        holder.timestampView.text = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date(message.timestamp))
    }

    override fun getItemCount(): Int = messages.size

    fun updateMessages(newMessages: List<Message>) {
        messages = newMessages
        notifyDataSetChanged()
    }
}
