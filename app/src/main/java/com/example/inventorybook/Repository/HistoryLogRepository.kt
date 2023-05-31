package com.example.inventorybook.Repository

import android.content.Context
import android.widget.Toast
import com.example.inventorybook.Interfaces.IHistoryLogRepository
import com.example.inventorybook.Modelos.HistoryLog
import com.example.inventorybook.Modelos.Paper
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot

class HistoryLogRepository: IHistoryLogRepository {

    private val database = FirebaseFirestore.getInstance()
    lateinit var historyLogs: MutableList<HistoryLog>

    override fun add(historyLog: HistoryLog, context: Context) {
        database.collection("history").document(historyLog.Id).set(
            hashMapOf("Id" to historyLog.Id, "PaperId" to historyLog.PaperId,
                        "Fecha" to historyLog.Fecha,
                        "Usuario" to historyLog.Usuario)
        ).addOnSuccessListener {
            Toast.makeText(context, "Registro con exito", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener{
            Toast.makeText(context, "Error en el registro", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getById() {
        TODO("Not yet implemented")
    }


    override fun getByPaperId(PaperId: String): Task<QuerySnapshot> {
        return database.collection("history").whereEqualTo("PaperId",PaperId).get()
    }
}