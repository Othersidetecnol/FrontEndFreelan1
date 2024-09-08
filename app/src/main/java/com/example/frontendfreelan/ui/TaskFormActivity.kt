package com.example.frontendfreelan.ui

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.frontendfreelan.databinding.ActivityTaskFormBinding
import java.util.Calendar

class TaskFormActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTaskFormBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTaskFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.inputDate.setOnClickListener {
            showDatePickerDialog()
        }

        binding.saveButton.setOnClickListener {
            // Ação ao clicar no botão salvar
            // Adicione aqui o código para salvar os dados
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
}
