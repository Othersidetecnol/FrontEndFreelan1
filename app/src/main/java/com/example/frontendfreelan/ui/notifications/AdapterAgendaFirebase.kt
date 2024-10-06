package com.example.frontendfreelan.ui.notifications

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.frontendfreelan.databinding.ItemAgendaCalendarioBinding

class AdapterAgendaFirebase : RecyclerView.Adapter<AdapterAgendaFirebase.NotificationViewHolder>() {

    private var tasks: List<Task> = listOf()

    fun submitList(newTasks: List<Task>) {
        tasks = newTasks
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        val binding = ItemAgendaCalendarioBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NotificationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        holder.bind(tasks[position])
    }

    override fun getItemCount(): Int = tasks.size

    class NotificationViewHolder(private val binding: ItemAgendaCalendarioBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(task: Task) {
            binding.notificationTitle.text = task.name
            binding.notificationDetails.text = task.details
            binding.notificationDate.text = task.date
            binding.notificationTime.text = task.time
            binding.notificationValue.text = task.value.toString()
        }
    }
}
