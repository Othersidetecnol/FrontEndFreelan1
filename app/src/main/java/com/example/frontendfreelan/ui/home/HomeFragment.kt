package com.example.frontendfreelan.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.frontendfreelan.databinding.FragmentHomeBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var binding: FragmentHomeBinding
    private lateinit var firestoreListener: ListenerRegistration

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Infla o layout usando View Binding
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inicializa o ViewModel
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        // Configura o RecyclerView
        binding.recyclerViewResult.layoutManager = LinearLayoutManager(context)
        val adapter = MyAdapter(emptyList(), false)
        binding.recyclerViewResult.adapter = adapter

        // Configura o botão para abrir a AddItemActivity
        binding.addItemButton.setOnClickListener {
            val intent = Intent(activity, AddItemActivity::class.java)
            startActivity(intent)
        }

        // Configura o listener do Firestore para atualizar os dados em tempo real
        val db = FirebaseFirestore.getInstance()
        firestoreListener = db.collection("items")
            .addSnapshotListener { snapshots, e ->
                if (e != null) {
                    // Trate o erro aqui
                    return@addSnapshotListener
                }

                if (snapshots != null) {
                    val items = snapshots.toObjects(ItemHome::class.java)
                    adapter.updateItems(items)
                }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Remove o listener do Firestore quando a view é destruída
        firestoreListener.remove()
    }
}
