package com.example.inventorybook.Repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.inventorybook.Interfaces.IPaperRepository
import com.example.inventorybook.Modelos.Paper
import com.google.firebase.firestore.FirebaseFirestore


class PaperRepository: IPaperRepository {

    private val database = FirebaseFirestore.getInstance()

    override fun add(paper: Paper): Boolean {
        return database.collection("papers").document(paper.Id).set(
            hashMapOf("Id" to paper.Id, "Titulo" to paper.Titulo,
            "Equipo" to paper.Equipo,
            "Modelo" to paper.Model,
            "Serial" to paper.Serial,
            "Factura" to paper.Factura,
            "Fecha" to paper.Fecha,
            "WindowsKey" to paper.WindowsKey,
            "Departamento" to paper.Departamento,
            "User" to paper.Usuario)
        ).isComplete
    }

    override fun getById(id: String): Paper {
        TODO("Not yet implemented")
    }

    override fun getAll(): MutableList<Paper> {
        val dataList = mutableListOf<Paper>()
        var res = database.collection("papers").get().addOnSuccessListener { resultado ->
            if(!resultado.isEmpty){
                for(document in resultado){
                    val paper = document.toObject(Paper::class.java)
                    dataList.add(paper)
                }

            }

        }
        Log.d("Usuarios: ", "$dataList")
        return dataList
    }

}