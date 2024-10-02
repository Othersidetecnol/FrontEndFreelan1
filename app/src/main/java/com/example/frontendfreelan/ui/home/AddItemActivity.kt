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
            val startDate = binding.startDate.text.toString()
            val endDate = binding.endDate.text.toString()

            // Certifique-se de que todos os parâmetros estão sendo passados corretamente
            val item = ItemHome(
                name_cliente = nameCliente,
                local_cliente = localCliente,
                title = title,
                category = category,
                value = value,
                description = description,
                startDate = startDate,
                endDate = endDate
            )

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
