package com.example.frontendfreelan
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.frontendfreelan.R
import com.example.frontendfreelan.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurar a Toolbar
        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Habilitar o botão de voltar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications,
                R.id.navigation_ordens, R.id.navigation_infos
            )
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        // Configurar o botão menu_usuario
        val btnMenuUsuario: Button = findViewById(R.id.menu_usuario)
        btnMenuUsuario.setOnClickListener { view ->
            // Criar o PopupMenu
            val popupMenu = PopupMenu(this, view)
            popupMenu.inflate(R.menu.menu_items)

            // Tratar clique dos itens do menu
            popupMenu.setOnMenuItemClickListener { menuItem: MenuItem ->
                when (menuItem.itemId) {
                    R.id.item_nome_usuario -> {
                        // Mostrar o nome do usuário (você pode personalizar isso)
                        Toast.makeText(this, "Nome do usuário logado", Toast.LENGTH_SHORT).show()
                        true
                    }
                    R.id.item_deslogar -> {
                        // Lógica para deslogar o usuário
                        Toast.makeText(this, "Deslogar", Toast.LENGTH_SHORT).show()
                        // Aqui você pode adicionar o código para deslogar o usuário
                        true
                    }
                    else -> false
                }
            }
            // Mostrar o PopupMenu
            popupMenu.show()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}
