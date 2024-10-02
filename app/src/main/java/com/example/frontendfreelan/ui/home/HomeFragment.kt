package com.example.frontendfreelan.ui.home

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.frontendfreelan.databinding.FragmentHomeBinding
import com.google.firebase.firestore.FirebaseFirestore

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: MyAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = MyAdapter(emptyList(), true)
        binding.recyclerViewResult.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewResult.adapter = adapter

        // Adiciona o TextWatcher ao campo de busca
        binding.searchField.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                adapter.filter(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        // Carrega os dados do Firestore
        loadItemsFromFirestore()
    }

    private fun loadItemsFromFirestore() {
        val db = FirebaseFirestore.getInstance()
        db.collection("items")
            .get()
            .addOnSuccessListener { result ->
                val items = result.toObjects(ItemHome::class.java)
                adapter.updateItems(items)
            }
            .addOnFailureListener { exception ->
                // Trate o erro aqui
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
