package com.example.inventorybook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.inventorybook.Modelos.Paper
import com.example.inventorybook.Repository.PaperRepository

class InventoryDetailActivity : AppCompatActivity() {

    private lateinit var listView: RecyclerView
    private lateinit var papers: MutableList<Paper>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inventory_detail)

        listView = findViewById(R.id.inventoryRecyclerView)
        listView.layoutManager = LinearLayoutManager(this)
        papers = mutableListOf()

        PaperRepository().getAll().addOnSuccessListener {
            for (document in it.documents) {
                val paper: Paper? = document.toObject(Paper::class.java)
                if (paper != null) {
                    papers.add(paper)
                }
            }
            listView.adapter = EquipoAdapter(this,papers)
        }


    }

}
