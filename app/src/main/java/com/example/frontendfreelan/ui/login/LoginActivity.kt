package com.example.frontendfreelan.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.frontendfreelan.MainActivity
import com.example.frontendfreelan.databinding.ActivityLoginBinding


class LoginActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityLoginBinding.inflate( layoutInflater )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView( binding.root )
        inicializarEventosClique()

    }

    private fun inicializarEventosClique() {
        binding.textCadastro.setOnClickListener {
            startActivity(
                Intent(this, CadastroActivity::class.java)
            )


        }

        binding.buttonLogin.setOnClickListener {
            startActivity(
                Intent(this, MainActivity::class.java)
            )
        }
    }
}