package com.example.frontendfreelan.ui.dailyschedule

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.frontendfreelan.databinding.ActivityDailyScheduleBinding
import com.example.frontendfreelan.ui.notifications.SharedViewModel
import com.example.frontendfreelan.ui.notifications.TaskAdapter

// Classe que representa a atividade de agendamento diário
class DailyScheduleActivity : AppCompatActivity() {

    // Variáveis para binding, adapter e ViewModel
    private lateinit var binding: ActivityDailyScheduleBinding
    private lateinit var taskAdapter: TaskAdapter
    private lateinit var sharedViewModel: SharedViewModel

    // Método onCreate é chamado quando a atividade é criada
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Infla o layout usando View Binding, que permite acessar as views diretamente
        binding = ActivityDailyScheduleBinding.inflate(layoutInflater)
        setContentView(binding.root) // Define o layout da atividade

        // Inicializa o ViewModel compartilhado, que será usado para gerenciar os dados da UI
        sharedViewModel = ViewModelProvider(this).get(SharedViewModel::class.java)

        // Inicializa o adapter com uma lista vazia e uma ação de clique para os itens
        taskAdapter = TaskAdapter(emptyList()) { task, position ->
            // Ação ao clicar em um item da lista
            // Aqui você pode definir o que acontece quando um item é clicado
        }
        // Log para depuração, para verificar se o RecyclerView e o Adapter foram inicializados corretamente
        Log.d("DailyScheduleActivity", "RecyclerView: ${binding.recyclerView}")
        Log.d("DailyScheduleActivity", "TaskAdapter: $taskAdapter")

        // Verifique se o RecyclerView não é nulo
        if (binding.recyclerView == null) {
            Log.e("DailyScheduleActivity", "RecyclerView é nulo!")
        } else {
            // Configura o layout manager e o adapter do RecyclerView
            binding.recyclerView.layoutManager = LinearLayoutManager(this) // Define o layout como uma lista vertical
            binding.recyclerView.adapter = taskAdapter // Define o adapter para o RecyclerView
        }

        // Observa as mudanças na lista de tarefas e atualiza o adapter quando os dados mudam
        sharedViewModel.tasks.observe(this) { taskList ->
            taskAdapter.updateTasks(taskList) // Atualiza a lista de tarefas no adapter
        }
    }
}
