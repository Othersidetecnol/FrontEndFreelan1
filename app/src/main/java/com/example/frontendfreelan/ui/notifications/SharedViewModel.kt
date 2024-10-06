package com.example.frontendfreelan.ui.notifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class SharedViewModel : ViewModel() {

    private val _tasks = MutableLiveData<List<NotificationsViewModel.Task>>()
    val tasks: LiveData<List<NotificationsViewModel.Task>> = _tasks

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    fun fetchTasksByDate(date: String) {
        val user = auth.currentUser
        if (user != null) {
            db.collection("users").document(user.uid).collection("tasks")
                .whereEqualTo("date", date)
                .get()
                .addOnSuccessListener { documents ->
                    val taskList = documents.map { document ->
                        document.toObject(NotificationsViewModel.Task::class.java)
                    }
                    _tasks.value = taskList
                }
                .addOnFailureListener { exception ->
                    // Handle the error
                }
        }
    }
}
