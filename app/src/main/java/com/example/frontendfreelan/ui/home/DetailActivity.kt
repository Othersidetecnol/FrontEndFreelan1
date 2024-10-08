package com.example.frontendfreelan.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.frontendfreelan.databinding.ActivityDetailBinding
import com.example.frontendfreelan.ui.chat.ChatActivity

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val nameCliente = intent.getStringExtra("nameCliente")
        val localCliente = intent.getStringExtra("localCliente")
        val title = intent.getStringExtra("title")
        val category = intent.getStringExtra("category")
        val description = intent.getStringExtra("description")
        val startDate = intent.getStringExtra("startDate")
        val endDate = intent.getStringExtra("endDate")

        binding.clienteName.text = nameCliente
        binding.clienteLocal.text = localCliente
        binding.detailTitle.text = title
        binding.detailCategory.text = category
        binding.detailDescription.text = description
        binding.startDate.text = startDate
        binding.endDate.text = endDate

        // Ação do botão Fechar
        binding.detailButton.setOnClickListener {
            finish() // Fecha a activity
        }

        // Ação do botão Iniciar Chat
        binding.btnIniciarChat.setOnClickListener {
            val intent = Intent(this, ChatActivity::class.java)
            startActivity(intent)
        }
    }
}
