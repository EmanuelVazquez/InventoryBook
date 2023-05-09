package com.example.inventorybook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.inventorybook.Repository.PaperRepository

class InventoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inventory)

        setup()
    }

    private fun setup(){
        val paperRepository = PaperRepository()

    }

    fun InventoryDetailAccess(view: View){
        val intent = Intent(this,InventoryDetailActivity::class.java)
        startActivity(intent)
    }
}