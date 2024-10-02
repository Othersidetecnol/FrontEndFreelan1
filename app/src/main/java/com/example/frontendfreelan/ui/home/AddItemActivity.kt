package com.example.frontendfreelan.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.frontendfreelan.databinding.ActivityAddItemBinding
import com.google.firebase.firestore.FirebaseFirestore

class AddItemActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddItemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.saveButton.setOnClickListener {
            val nameCliente = binding.nameCliente.text.toString()
            val localCliente = binding.localCliente.text.toString()
            val title = binding.title.text.toString()
            val category = binding.category.text.toString()
            val value = binding.value.text.toString()
            val description = binding.description.text.toString()

            val item = ItemHome(nameCliente, localCliente, title, category, value, description)

            val db = FirebaseFirestore.getInstance()
            db.collection("items")
                .add(item)
                .addOnSuccessListener {
                    // Item adicionado com sucesso
                    finish() // Fecha a Activity
                }
                .addOnFailureListener { e ->
                    // Trate o erro aqui
                }
        }
    }
}