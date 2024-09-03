package com.example.frontendfreelan.ui.home

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.frontendfreelan.R

data class Item(val title: String, val location: String, val category: String, val value: String, val description: String)

class MyAdapter(private val items: List<Item>) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleView: TextView = view.findViewById(R.id.item_title)
        val locationView: TextView = view.findViewById(R.id.item_location)
        val categoryView: TextView = view.findViewById(R.id.item_category)
        val valueView: TextView = view.findViewById(R.id.item_value)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.titleView.text = item.title
        holder.locationView.text = item.location
        holder.categoryView.text = item.category
        holder.valueView.text = item.value

        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, DetailActivity::class.java).apply {
                putExtra("title", item.title)
                putExtra("category", item.category)
                putExtra("description", item.description)
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = items.size
}
