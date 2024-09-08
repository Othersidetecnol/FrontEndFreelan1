package com.example.frontendfreelan.ui.notifications

import android.os.Parcel
import android.os.Parcelable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel

class NotificationsViewModel : ViewModel() {

    // LiveData para a lista de tarefas
    private val _tasks = MutableLiveData<List<Task>>().apply {
        value = listOf() // Lista vazia inicial
    }
    val tasks: LiveData<List<Task>> = _tasks

    // LiveData para a data selecionada
    private val _selectedDate = MutableLiveData<String>()
    val selectedDate: LiveData<String> = _selectedDate

    // LiveData derivado que filtra as tarefas pela data selecionada
    val tasksForSelectedDate: LiveData<List<Task>> = MediatorLiveData<List<Task>>().apply {
        addSource(tasks) { taskList ->
            value = taskList.filter { it.date == _selectedDate.value }
        }
        addSource(selectedDate) { date ->
            value = tasks.value?.filter { it.date == date }
        }
    }

    // Classe de dados Task
    data class Task(
        val title: String,
        val date: String,  // Formato: "dd/MM/yyyy"
        val time: String,
        val description: String,
        val value: Double
    ) : Parcelable {
        constructor(parcel: Parcel) : this(
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readDouble()
        )

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeString(title)
            parcel.writeString(date)
            parcel.writeString(time)
            parcel.writeString(description)
            parcel.writeDouble(value)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<Task> {
            override fun createFromParcel(parcel: Parcel): Task {
                return Task(parcel)
            }

            override fun newArray(size: Int): Array<Task?> {
                return arrayOfNulls(size)
            }
        }
    }

    // Método para adicionar uma tarefa
    fun addTask(task: Task) {
        _tasks.value = _tasks.value?.plus(task)
    }

    // Método para atualizar uma tarefa
    fun updateTask(index: Int, task: Task) {
        _tasks.value = _tasks.value?.toMutableList()?.apply {
            set(index, task)
        }
    }

    // Método para definir a data selecionada
    fun setSelectedDate(date: String) {
        _selectedDate.value = date
    }
}
