package com.example.inventorybook.Repository

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.inventorybook.Interfaces.IPaperRepository
import com.example.inventorybook.Modelos.Paper
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.tasks.await


class PaperRepository: IPaperRepository {

    private val database = FirebaseFirestore.getInstance()
    lateinit var papers: MutableList<Paper>

    override fun add(paper: Paper, context: Context) {
        database.collection("papers").document(paper.Id).set(
            hashMapOf("Id" to paper.Id, "Titulo" to paper.Titulo,
                "Equipo" to paper.Equipo,
                "Modelo" to paper.Modelo,
                "Serial" to paper.Serial,
                "Factura" to paper.Factura,
                "Fecha" to paper.Fecha,
                "WindowsKey" to paper.WindowsKey,
                "Departamento" to paper.Departamento,
                "User" to paper.User)
        ).addOnSuccessListener {
            Toast.makeText(context, "Registro con exito", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener{
            Toast.makeText(context, "Fallo en el registro", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getById(id: String): Task<DocumentSnapshot> {
        return database.collection("papers").document(id).get()
    }

    override fun getAll(): Task<QuerySnapshot> {
        return database.collection("papers").get()
    }

    fun getDocumentList(): Task<QuerySnapshot> {
        return database.collection("nombreDeLaColeccion").get()
    }
}