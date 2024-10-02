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

    // Declaração das variáveis para o binding e o ViewModel compartilhado
    private lateinit var binding: ActivityTaskFormBinding
    private lateinit var sharedViewModel: SharedViewModel

    // Função chamada quando a atividade é criada
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inicializa o binding com o layout da atividade
        binding = ActivityTaskFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializa o ViewModel compartilhado
        sharedViewModel = ViewModelProvider(this).get(SharedViewModel::class.java)

        // Define o comportamento do clique no campo de data
        binding.inputDate.setOnClickListener {
            showDatePickerDialog()
        }

        // Define o comportamento do clique no botão de salvar
        binding.saveButton.setOnClickListener {
            saveTask()
            finish() // Fecha a atividade e retorna ao fragmento anterior
        }
    }

    // Função para exibir o DatePickerDialog e selecionar uma data
    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        // Cria e exibe o DatePickerDialog
        val datePickerDialog = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDay ->
                // Formata a data selecionada e define no campo de data
                val selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                binding.inputDate.setText(selectedDate)
            },
            year, month, day
        )
        datePickerDialog.show()
    }

    // Função para salvar a tarefa
    private fun saveTask() {
        // Obtém os valores dos campos de entrada
        val name = binding.inputName.text.toString()
        val details = binding.inputDetails.text.toString()
        val date = binding.inputDate.text.toString()
        val time = binding.inputTime.text.toString()
        val value = binding.inputValue.text.toString().toDoubleOrNull() ?: 0.0

        // Cria uma nova tarefa com os valores obtidos
        val task = SharedViewModel.Task(name, date, time, details, value)
        // Adiciona a tarefa ao ViewModel compartilhado
        sharedViewModel.addTask(task)

        // Adiciona uma mensagem de log para registrar a ação de salvar
        Log.d("TaskFormActivity", "Task saved: $task")
    }
}
