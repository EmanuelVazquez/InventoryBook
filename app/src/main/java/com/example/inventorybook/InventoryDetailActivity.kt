package com.example.inventorybook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.inventorybook.Modelos.Paper
import com.example.inventorybook.Repository.PaperRepository

class InventoryDetailActivity : AppCompatActivity() {

    private lateinit var listView: RecyclerView
    lateinit var paperList: MutableList<Paper>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inventory_detail)

        listView = findViewById(R.id.inventoryRecyclerView)
        paperList = PaperRepository().getAll()

        establecerAdaptador()
    }

    private fun establecerAdaptador(){
        listView.layoutManager = LinearLayoutManager(this)
        listView.adapter = EquipoAdapter(this,paperList)
    }

}