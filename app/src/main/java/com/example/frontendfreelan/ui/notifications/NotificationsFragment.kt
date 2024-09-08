package com.example.frontendfreelan.ui.notifications

import TaskFormActivity
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.frontendfreelan.databinding.FragmentNotificationsBinding
import com.example.frontendfreelan.ui.DailyScheduleActivity


class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null
    private val binding get() = _binding!!
    private lateinit var notificationsViewModel: NotificationsViewModel
    private lateinit var adapter: TaskAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        notificationsViewModel = ViewModelProvider(this).get(NotificationsViewModel::class.java)
        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        adapter = TaskAdapter(emptyList()) { task, position ->
            val intent = Intent(activity, TaskFormActivity::class.java).apply {
                putExtra("task", task)
                putExtra("position", position)
            }
            startActivityForResult(intent, REQUEST_CODE_EDIT_TASK)
        }

        binding.notificationsRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.notificationsRecyclerView.adapter = adapter

        // Observa as tarefas filtradas pela data selecionada
        notificationsViewModel.tasksForSelectedDate.observe(viewLifecycleOwner) { tasks ->
            adapter.updateTasks(tasks)
            Log.d("NotificationsFragment", "Tasks updated: ${tasks.size}")
        }

        // Configura o comportamento do CalendarView
        binding.calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val date = String.format("%02d/%02d/%04d", dayOfMonth, month + 1, year)
            Toast.makeText(requireContext(), "Data selecionada: $date", Toast.LENGTH_SHORT).show()

            // Verifica se há tarefas para a data selecionada
            val tasksForDate = notificationsViewModel.tasks.value?.filter { it.date == date }
            if (!tasksForDate.isNullOrEmpty()) {
                // Inicia a DailyScheduleActivity se houver tarefas
                val intent = Intent(activity, DailyScheduleActivity::class.java).apply {
                    putExtra("selected_date", date)
                }
                startActivity(intent)
            } else {
                // Exibe uma mensagem informando que não há tarefas para a data selecionada
                Toast.makeText(requireContext(), "Nenhuma tarefa para a data selecionada", Toast.LENGTH_SHORT).show()
            }
        }

        binding.addNotificationButton.setOnClickListener {
            val intent = Intent(activity, TaskFormActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE_ADD_TASK)
        }

        return root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            data?.let {
                val task = it.getParcelableExtra<NotificationsViewModel.Task>("task")
                val position = it.getIntExtra("position", -1)
                if (task != null) {
                    if (requestCode == REQUEST_CODE_ADD_TASK) {
                        notificationsViewModel.addTask(task)
                    } else if (requestCode == REQUEST_CODE_EDIT_TASK && position != -1) {
                        notificationsViewModel.updateTask(position, task)
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val REQUEST_CODE_ADD_TASK = 1
        private const val REQUEST_CODE_EDIT_TASK = 2
    }
}
