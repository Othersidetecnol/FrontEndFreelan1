package com.example.frontendfreelan.ui.notifications

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.frontendfreelan.databinding.FragmentNotificationsBinding
import com.example.frontendfreelan.ui.ResultScheduleActivity
import com.example.frontendfreelan.ui.TaskFormActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private lateinit var adapter: AdapterAgendaFirebase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        setupRecyclerView()
        loadTasks()

        binding.calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val selectedDate = "$dayOfMonth/${month + 1}/$year"
            loadTasksByDate(selectedDate)
        }

        binding.addNotificationButton.setOnClickListener {
            openTaskFormActivity()
        }

        return root
    }

    private fun setupRecyclerView() {
        adapter = AdapterAgendaFirebase()
        binding.notificationsRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.notificationsRecyclerView.adapter = adapter
    }

    private fun loadTasks() {
        val user = auth.currentUser
        if (user != null) {
            db.collection("users").document(user.uid).collection("tasks")
                .get()
                .addOnSuccessListener { result ->
                    val tasks = result.map { document -> document.toObject(Task::class.java) }
                    adapter.submitList(tasks)
                }
                .addOnFailureListener { exception ->
                    // Handle the error
                }
        }
    }

    private fun loadTasksByDate(date: String) {
        val user = auth.currentUser
        if (user != null) {
            db.collection("users").document(user.uid).collection("tasks")
                .whereEqualTo("date", date)
                .get()
                .addOnSuccessListener { result ->
                    val tasks = result.map { document -> document.toObject(Task::class.java) }
                    adapter.submitList(tasks)
                }
                .addOnFailureListener { exception ->
                    // Handle the error
                }
        }
    }

    private fun openResultScheduleActivity(date: String) {
        val intent = Intent(requireContext(), ResultScheduleActivity::class.java)
        intent.putExtra("selectedDate", date)
        startActivity(intent)
    }

    private fun openTaskFormActivity() {
        val intent = Intent(requireContext(), TaskFormActivity::class.java)
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private val taskSavedReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            loadTasks()
        }
    }

    override fun onResume() {
        super.onResume()
        val filter = IntentFilter("com.example.frontendfreelan.TASK_SAVED")
        requireContext().registerReceiver(taskSavedReceiver, filter, Context.RECEIVER_NOT_EXPORTED)
    }

    override fun onPause() {
        super.onPause()
        requireContext().unregisterReceiver(taskSavedReceiver)
    }
}
