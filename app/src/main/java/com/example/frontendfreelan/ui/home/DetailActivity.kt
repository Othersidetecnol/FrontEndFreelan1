package com.example.frontendfreelan.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.frontendfreelan.R
import com.example.frontendfreelan.databinding.ActivityDetailBinding
import com.example.frontendfreelan.ui.chat.ChatActivity

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val title = intent.getStringExtra("title")
        val category = intent.getStringExtra("category")
        val description = intent.getStringExtra("description")

        binding.detailTitle.text = title
        binding.detailCategory.text = category
        binding.detailDescription.text = description

        binding.detailButton.setOnClickListener {
            // Ação do botão Fechar
            finish()
        }

        binding.button.setOnClickListener {
            // Ação do botão Iniciar Chat
            val intent = Intent(this, ChatActivity::class.java)
            startActivity(intent)
        }
    }
}
