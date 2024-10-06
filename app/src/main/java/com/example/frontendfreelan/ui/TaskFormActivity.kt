package com.example.frontendfreelan.ui

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.frontendfreelan.databinding.ActivityTaskFormBinding
import com.example.frontendfreelan.ui.notifications.SharedViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.util.Calendar

class TaskFormActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTaskFormBinding
    private lateinit var sharedViewModel: SharedViewModel
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTaskFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedViewModel = ViewModelProvider(this).get(SharedViewModel::class.java)

        // Inicializa Firebase Auth e Firestore
        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        binding.inputDate.setOnClickListener {
            showDatePickerDialog()
        }

        binding.saveButton.setOnClickListener {
            saveTask()
        }
    }

    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDay ->
                val selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                binding.inputDate.setText(selectedDate)
            },
            year, month, day
        )
        datePickerDialog.show()
    }

    private fun saveTask() {
        val name = binding.inputName.text.toString()
        val details = binding.inputDetails.text.toString()
        val date = binding.inputDate.text.toString()
        val time = binding.inputTime.text.toString()
        val value = binding.inputValue.text.toString().toDoubleOrNull() ?: 0.0

        val task = hashMapOf(
            "name" to name,
            "details" to details,
            "date" to date,
            "time" to time,
            "value" to value
        )

        val user = auth.currentUser
        if (user != null) {
            db.collection("users").document(user.uid).collection("tasks")
                .add(task)
                .addOnSuccessListener { documentReference ->
                    Log.d("TaskFormActivity", "Task added with ID: ${documentReference.id}")
                    finish() // Finaliza a atividade após salvar a tarefa
                }
                .addOnFailureListener { e ->
                    Log.w("TaskFormActivity", "Error adding task", e)
                }
        } else {
            Log.w("TaskFormActivity", "No user is logged in")
        }
    }
}
