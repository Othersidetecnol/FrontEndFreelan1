package com.example.frontendfreelan.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.frontendfreelan.databinding.FragmentHomeBinding
import com.seu.pacote.SpaceItemDecoration

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val recyclerView: RecyclerView = binding.recyclerViewResult
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.addItemDecoration(SpaceItemDecoration(4)) // Adiciona 4dp de espaçamento
        homeViewModel.items.observe(viewLifecycleOwner) { items ->
            recyclerView.adapter = MyAdapter(items, false)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
