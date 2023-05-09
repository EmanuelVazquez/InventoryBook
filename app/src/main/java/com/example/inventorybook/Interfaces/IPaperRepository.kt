package com.example.inventorybook.Interfaces

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.example.inventorybook.Modelos.Paper
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot

interface IPaperRepository {

    fun getById(id: String): Task<DocumentSnapshot>
    fun getAll(): Task<QuerySnapshot>
    fun add(paper: Paper, context: Context)
}