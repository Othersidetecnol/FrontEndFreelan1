package com.example.frontendfreelan.ui.notifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {

    private val _tasks = MutableLiveData<List<Task>>().apply {
        value = listOf()
    }
    val tasks: LiveData<List<Task>> = _tasks

    fun addTask(task: Task) {
        _tasks.value = _tasks.value?.plus(task)
    }

    data class Task(
        val title: String,
        val date: String,
        val time: String,
        val description: String,
        val value: Double
    )
}
