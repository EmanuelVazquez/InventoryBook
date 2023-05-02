package com.example.inventorybook.Interfaces

import androidx.lifecycle.MutableLiveData
import com.example.inventorybook.Modelos.Paper

interface IPaperRepository {
    fun add(paper: Paper): Boolean
    fun getById(id: String): Paper
    fun getAll(): MutableList<Paper>
}