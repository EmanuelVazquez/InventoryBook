package com.example.inventorybook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

enum class ProviderType{
    BASIC
}
class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
    }

    fun accessNewPaper(view: View) {
        val intent = Intent(this,NewPaperActivity::class.java)
        startActivity(intent)
    }

    fun accesInventory(view: View){
        val intent = Intent(this,InventoryActivity::class.java)
        startActivity(intent)
    }
}