package com.example.frontendfreelan.ui

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.frontendfreelan.databinding.ActivityTaskFormBinding
import com.example.frontendfreelan.ui.notifications.SharedViewModel
import java.util.Calendar

class TaskFormActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTaskFormBinding
    private lateinit var sharedViewModel: SharedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTaskFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedViewModel = ViewModelProvider(this).get(SharedViewModel::class.java)

        binding.inputDate.setOnClickListener {
            showDatePickerDialog()
        }

        binding.saveButton.setOnClickListener {
            saveTask()
            finish() // Fecha a atividade e retorna ao fragmento anterior
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

        val task = SharedViewModel.Task(name, date, time, details, value)
        sharedViewModel.addTask(task)

        // Adiciona uma mensagem de log para registrar a ação de salvar
        Log.d("TaskFormActivity", "Task saved: $task")
    }
}
