package com.example.frontendfreelan.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.frontendfreelan.R
import com.example.frontendfreelan.ui.notifications.NotificationsViewModel
import com.example.frontendfreelan.ui.notifications.TaskAdapter
import com.google.firebase.firestore.FirebaseFirestore
import android.widget.Button

class ResultScheduleActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: TaskAdapter
    private var taskList: MutableList<NotificationsViewModel.Task> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.daily_schedule) // Certifique-se de que o nome do layout está correto

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = TaskAdapter(taskList) { task, position ->
            // Handle item click
        }
        recyclerView.adapter = adapter

        // Supondo que você tenha um método para obter a data selecionada
        val selectedDate = getSelectedDate()
        loadTasksForDate(selectedDate)

        // Adicionando o OnClickListener ao botão "Voltar"
        val btnVoltar: Button = findViewById(R.id.btnvoltar)
        btnVoltar.setOnClickListener {
            finish()
        }
    }

    fun fetchTasksByDate(date: String, onSuccess: (List<NotificationsViewModel.Task>) -> Unit, onFailure: (Exception) -> Unit) {
        val db = FirebaseFirestore.getInstance()
        db.collection("tasks")
            .whereEqualTo("date", date)
            .get()
            .addOnSuccessListener { queryDocumentSnapshots ->
                val tasks = queryDocumentSnapshots.documents.mapNotNull { it.toObject(
                    NotificationsViewModel.Task::class.java) }
                onSuccess(tasks)
            }
            .addOnFailureListener { exception ->
                onFailure(exception)
            }
    }

    private fun loadTasksForDate(date: String) {
        fetchTasksByDate(date, { tasks ->
            taskList.clear()
            taskList.addAll(tasks)
            adapter.notifyDataSetChanged()
        }, { exception ->
            // Tratar falha
        })
    }

    private fun getSelectedDate(): String {
        // Implementar lógica para obter a data selecionada
        return "2024-10-03" // Exemplo
    }
}
