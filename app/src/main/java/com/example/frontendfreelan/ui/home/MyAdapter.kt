package com.example.frontendfreelan.ui.home

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.frontendfreelan.databinding.ItemLayoutBinding

class MyAdapter(private var items: List<ItemHome>, private val isEditable: Boolean) :
    RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    private var originalItems: List<ItemHome> = items

    class ViewHolder(val binding: ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.binding.nameCliente.text = item.name_cliente
        holder.binding.localCliente.text = item.local_cliente
        holder.binding.title.text = item.title
        holder.binding.category.text = item.category
        holder.binding.value.text = item.value
        holder.binding.description.text = item.description

        // Adiciona o OnClickListener para abrir a DetailActivity
        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, DetailActivity::class.java).apply {
                putExtra("nameCliente", item.name_cliente)
                putExtra("localCliente", item.local_cliente)
                putExtra("title", item.title)
                putExtra("category", item.category)
                putExtra("description", item.description)
                putExtra("startDate", item.startDate)
                putExtra("endDate", item.endDate)
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = items.size

    fun updateItems(newItems: List<ItemHome>) {
        originalItems = newItems
        items = newItems
        notifyDataSetChanged()
    }

    fun filter(query: String) {
        items = if (query.isEmpty()) {
            originalItems
        } else {
            originalItems.filter { it.category.contains(query, ignoreCase = true) }
        }
        notifyDataSetChanged()
    }
}
