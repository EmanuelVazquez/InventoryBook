package com.example.inventorybook.Interfaces

import android.content.Context
import com.example.inventorybook.Modelos.HistoryLog
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.QuerySnapshot

interface IHistoryLogRepository {
    fun add(historyLog: HistoryLog, context: Context)
    fun getById()
    fun getByPaperId(PaperId: String): Task<QuerySnapshot>
}