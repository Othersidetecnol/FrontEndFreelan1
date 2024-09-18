package com.example.frontendfreelan.ui.notifications

import android.content.Intent
import android.os.Bundle
import android.widget.CalendarView
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.frontendfreelan.databinding.FragmentNotificationsBinding
import com.example.frontendfreelan.ui.ResultScheduleActivity
import com.example.frontendfreelan.ui.TaskFormActivity

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
            openResultScheduleActivity(selectedDate)
        }

        binding.addNotificationButton.setOnClickListener {
            openTaskFormActivity()
        }

        return root
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
}
