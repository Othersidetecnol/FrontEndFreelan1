package com.example.frontendfreelan.ui.dashboard

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.frontendfreelan.R
import com.example.frontendfreelan.ui.chat.ChatActivity

class ServiceAdapter(private val items: List<ServiceItem>) : RecyclerView.Adapter<ServiceAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val clientView: TextView = view.findViewById(R.id.item_client)
        val dateView: TextView = view.findViewById(R.id.item_date)
        val timeView: TextView = view.findViewById(R.id.item_time)
        val titleView: TextView = view.findViewById(R.id.item_title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout_dashboard, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.clientView.text = item.client
        holder.dateView.text = item.date
        holder.timeView.text = item.time
        holder.titleView.text = item.title

        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, ChatActivity::class.java).apply {
                putExtra("title", item.title)
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = items.size
}
