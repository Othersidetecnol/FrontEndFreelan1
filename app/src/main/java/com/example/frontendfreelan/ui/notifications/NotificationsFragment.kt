package com.example.frontendfreelan.ui.notifications

import android.content.Intent
import android.os.Bundle
import android.widget.CalendarView
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.frontendfreelan.databinding.FragmentNotificationsBinding
import com.example.frontendfreelan.ui.ScheduleActivity
import com.example.frontendfreelan.ui.Task
import java.util.Calendar

class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val selectedDate = "$dayOfMonth/${month + 1}/$year"
            openScheduleActivity(selectedDate)
        }

        binding.addNotificationButton.setOnClickListener {
            // Ação ao clicar no botão adicionar notificação
        }

        return root
    }

    private fun openScheduleActivity(date: String) {
        val taskList = getTasksForDate(date) // Função para obter as tarefas salvas para a data selecionada
        val intent = Intent(requireContext(), ScheduleActivity::class.java)
        intent.putParcelableArrayListExtra("taskList", ArrayList(taskList))
        startActivity(intent)
    }

    private fun getTasksForDate(date: String): List<NotificationsViewModel.Task> {
        // Implemente a lógica para obter as tarefas salvas para a data selecionada
        return listOf(
            NotificationsViewModel.Task("Task 1", "Details 1", date, "10:00", "Value 1"),
            NotificationsViewModel.Task("Task 2", "Details 2", date, "11:00", "Value 2")
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
