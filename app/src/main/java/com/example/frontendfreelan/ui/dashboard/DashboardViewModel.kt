package com.example.frontendfreelan.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

data class ServiceItem(val client: String, val date: String, val time: String, val title: String)

class DashboardViewModel : ViewModel() {

    private val _items = MutableLiveData<List<ServiceItem>>().apply {
        value = listOf(
            ServiceItem("Ana Silva", "01/09/2024", "10:00", "Reparo de Eletrodomésticos"),
            ServiceItem("Carlos Oliveira", "02/09/2024", "11:00", "Manutenção de Jardins"),
            ServiceItem("Mariana Santos", "03/09/2024", "12:00", "Instalação de Ar-Condicionado"),
            ServiceItem("Pedro Costa", "04/09/2024", "13:00", "Reforma de Banheiros"),
            ServiceItem("Fernanda Almeida", "05/09/2024", "14:00", "Aulas de Inglês"),
            ServiceItem("João Pereira", "06/09/2024", "15:00", "Serviço de Babá"),
            ServiceItem("Beatriz Lima", "07/09/2024", "16:00", "Fotografia de Eventos"),
            ServiceItem("Lucas Rocha", "08/09/2024", "17:00", "Desenvolvimento de Sites"),
            ServiceItem("Mariana Souza", "09/09/2024", "18:00", "Limpeza Residencial"),
            ServiceItem("Gustavo Ribeiro", "10/09/2024", "19:00", "Manutenção de Computadores"),
            ServiceItem("Juliana Fernandes", "11/09/2024", "20:00", "Aulas de Yoga"),
            ServiceItem("Ricardo Martins", "12/09/2024", "21:00", "Consultoria Financeira"),
            ServiceItem("Camila Silva", "13/09/2024", "22:00", "Pintura de Interiores"),
            ServiceItem("Felipe Cardoso", "14/09/2024", "23:00", "Tradução de Documentos"),
            ServiceItem("Isabela Gomes", "15/09/2024", "00:00", "Serviço de Encanador"),
            ServiceItem("Rodrigo Almeida", "16/09/2024", "01:00", "Consultoria de Marketing"),
            ServiceItem("Sofia Costa", "17/09/2024", "02:00", "Aulas de Violão"),
            ServiceItem("Matheus Castro", "18/09/2024", "03:00", "Manutenção de Automóveis"),
            ServiceItem("Laura Moreira", "19/09/2024", "04:00", "Assistência Técnica de Celulares"),
            ServiceItem("Vinícius Barbosa", "20/09/2024", "05:00", "Pet Sitting"),
            ServiceItem("Gabriela Nunes", "21/09/2024", "06:00", "Aulas de Dança"),
            ServiceItem("Daniel Freitas", "22/09/2024", "07:00", "Consultoria de Recursos Humanos"),
            ServiceItem("Letícia Duarte", "23/09/2024", "08:00", "Manutenção de Piscinas")

        )
    }
    val items: LiveData<List<ServiceItem>> = _items
}
