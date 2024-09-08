package com.example.frontendfreelan.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.frontendfreelan.ui.notifications.NotificationsViewModel

class DailyScheduleViewModel : ViewModel() {

    private val _tasks = MutableLiveData<List<NotificationsViewModel.Task>>().apply {
        value = listOf() // Lista inicial vazia
    }
    val tasks: LiveData<List<NotificationsViewModel.Task>> = _tasks

    private val _selectedDate = MutableLiveData<String>()
    val selectedDate: LiveData<String> = _selectedDate

    val tasksForSelectedDate: LiveData<List<NotificationsViewModel.Task>> = MediatorLiveData<List<NotificationsViewModel.Task>>().apply {
        addSource(tasks) { taskList ->
            value = taskList.filter { it.date == _selectedDate.value }
        }
        addSource(selectedDate) { date ->
            value = tasks.value?.filter { it.date == date }
        }
    }

    fun setSelectedDate(date: String?) {
        _selectedDate.value = date
    }

    fun setTasks(taskList: List<NotificationsViewModel.Task>) {
        _tasks.value = taskList
    }
}