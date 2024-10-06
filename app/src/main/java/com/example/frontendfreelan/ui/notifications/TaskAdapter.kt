package com.example.frontendfreelan.ui.notifications

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.frontendfreelan.R

class TaskAdapter(
    private var tasks: List<NotificationsViewModel.Task>,
    private val onItemClick: (NotificationsViewModel.Task, Int) -> Unit
) : RecyclerView.Adapter<TaskAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleView: TextView = view.findViewById(R.id.notification_name)
        val detailsView: TextView = view.findViewById(R.id.notification_details)
        val dateView: TextView = view.findViewById(R.id.notification_date)
        val timeView: TextView = view.findViewById(R.id.notification_time)
        val valueView: TextView = view.findViewById(R.id.notification_value)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_notification, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val task = tasks[position]
        holder.titleView.text = task.title
        holder.detailsView.text = task.description
        holder.dateView.text = task.date
        holder.timeView.text = task.time
        holder.valueView.text = task.value.toString()

        holder.itemView.setOnClickListener {
            onItemClick(task, position)
        }
    }

    override fun getItemCount(): Int = tasks.size

    fun updateTasks(newTasks: List<NotificationsViewModel.Task>) {
        tasks = newTasks
        notifyDataSetChanged()
    }
}
