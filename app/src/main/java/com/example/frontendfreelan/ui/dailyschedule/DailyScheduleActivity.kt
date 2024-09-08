package com.example.frontendfreelan.ui

import com.example.frontendfreelan.ui.TaskFormActivity
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.frontendfreelan.databinding.ActivityDailyScheduleBinding
import com.example.frontendfreelan.ui.notifications.NotificationsViewModel
import com.example.frontendfreelan.ui.notifications.TaskAdapter

class DailyScheduleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDailyScheduleBinding
    private lateinit var viewModel: DailyScheduleViewModel
    private lateinit var adapter: TaskAdapter

    private val taskFormRequestCode = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDailyScheduleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(DailyScheduleViewModel::class.java)
        adapter = TaskAdapter(emptyList()) { task, position ->
            // Handle item click if needed
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        val selectedDate = intent.getStringExtra("selected_date")
        viewModel.setSelectedDate(selectedDate)

        viewModel.tasksForSelectedDate.observe(this) { tasks ->
            adapter.updateTasks(tasks)
        }

        // Adicionar um clique para abrir a TaskFormActivity
        binding.addNotificationButton.setOnClickListener {
            val intent = Intent(this, TaskFormActivity::class.java)
            startActivityForResult(intent, taskFormRequestCode)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == taskFormRequestCode && resultCode == Activity.RESULT_OK) {
            val task = data?.getParcelableExtra<NotificationsViewModel.Task>("task")
            val position = data?.getIntExtra("position", -1)

            task?.let {
                if (position != null && position != -1) {
                    // Atualiza a tarefa existente
                    val tasks = viewModel.tasks.value?.toMutableList() ?: mutableListOf()
                    tasks[position] = it
                    viewModel.setTasks(tasks)
                } else {
                    // Adiciona nova tarefa
                    val tasks = viewModel.tasks.value?.toMutableList() ?: mutableListOf()
                    tasks.add(it)
                    viewModel.setTasks(tasks)
                }
            }
        }
    }
}
