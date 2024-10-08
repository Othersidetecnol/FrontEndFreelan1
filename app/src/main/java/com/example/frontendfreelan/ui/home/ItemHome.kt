package com.example.frontendfreelan.ui.home

import java.io.Serializable

data class ItemHome(
    val userId: String = "",
    val name_cliente: String = "",
    val local_cliente: String = "",
    val title: String = "",
    val category: String = "",
    val value: String = "",
    val description: String = "",
    val startDate: String = "",
    val endDate: String = ""
) : Serializable {
    // Construtor sem argumentos necessário para o Firebase Firestore
    constructor() : this("", "", "", "", "", "", "", "")
}
