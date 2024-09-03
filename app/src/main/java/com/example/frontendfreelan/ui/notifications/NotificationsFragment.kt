package com.example.frontendfreelan.ui.notifications

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.frontendfreelan.databinding.FragmentNotificationsBinding

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

        notificationsViewModel.tasks.observe(viewLifecycleOwner) { tasks ->
            adapter.updateTasks(tasks)
        }

        binding.addNotificationButton.setOnClickListener {
            val intent = Intent(activity, TaskFormActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE_ADD_TASK)
        }

        return root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && data != null) {
            val task = data.getParcelableExtra<NotificationsViewModel.Task>("task")
            val position = data.getIntExtra("position", -1)

            task?.let {
                if (requestCode == REQUEST_CODE_ADD_TASK) {
                    notificationsViewModel.addTask(it)
                } else if (requestCode == REQUEST_CODE_EDIT_TASK && position != -1) {
                    notificationsViewModel.updateTask(position, it)
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
