package com.example.frontendfreelan.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.frontendfreelan.databinding.ActivityResultScheduleBinding
import com.example.frontendfreelan.ui.notifications.SharedViewModel
import com.example.frontendfreelan.ui.notifications.TaskAdapter

class ResultScheduleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultScheduleBinding
    private lateinit var taskAdapter: TaskAdapter
    private lateinit var sharedViewModel: SharedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultScheduleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedViewModel = ViewModelProvider(this).get(SharedViewModel::class.java)

        taskAdapter = TaskAdapter(emptyList()) { task, position ->
            // Ação ao clicar em um item da lista
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = taskAdapter

        sharedViewModel.tasks.observe(this) { taskList ->
            taskAdapter.updateTasks(taskList)
        }

        binding.btnVoltar.setOnClickListener {
            finish()
        }
    }
}
