package com.example.frontendfreelan.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore

class HomeViewModel : ViewModel() {

    // LiveData que contém a lista de itens
    private val _items = MutableLiveData<List<ItemHome>>()
    val items: LiveData<List<ItemHome>> = _items

    // Inicializa o ViewModel buscando os itens do Firestore
    init {
        fetchItemsFromFirestore()
    }

    // Função para buscar itens do Firestore
    private fun fetchItemsFromFirestore() {
        val db = FirebaseFirestore.getInstance()
        db.collection("items")
            .get()
            .addOnSuccessListener { result ->
                val itemList = mutableListOf<ItemHome>()
                for (document in result) {
                    // Converte o documento do Firestore para um objeto ItemHome
                    val item = document.toObject(ItemHome::class.java)
                    itemList.add(item)
                }
                // Atualiza o valor do LiveData com a lista de itens
                _items.value = itemList
            }
            .addOnFailureListener { exception ->
                // Trate o erro aqui
                // Por exemplo, você pode registrar o erro ou mostrar uma mensagem ao usuário
            }
    }
}
