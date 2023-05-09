package com.example.inventorybook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.zxing.integration.android.IntentIntegrator

enum class ProviderType{
    BASIC
}
class MenuActivity : AppCompatActivity() {
    private lateinit var qrScanIntegrator: IntentIntegrator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        qrScanIntegrator = IntentIntegrator(this)


    }

    fun performAction(view:View){
        qrScanIntegrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES)
        qrScanIntegrator.setPrompt("lector - CDP")
        qrScanIntegrator.setCameraId(0)
        qrScanIntegrator.setBeepEnabled(true)
        qrScanIntegrator.setBarcodeImageEnabled(true)
        qrScanIntegrator.initiateScan()

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        val result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data)
        if (result!= null){
            if (result.contents == null){
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()

            }else{
                val intent = Intent(this,NewPaperActivity::class.java)
                intent.putExtra("Id",result.contents)
                startActivity(intent)
                Toast.makeText(this, "El valor escaneado es: ${result.contents}", Toast.LENGTH_SHORT).show()
            }

        }else{
            super.onActivityResult(requestCode, resultCode, data)
        }
    }


    fun accessNewPaper(view: View) {
        val intent = Intent(this,NewPaperActivity::class.java)
        startActivity(intent)
    }

    fun accesInventory(view: View){
        val intent = Intent(this,InventoryDetailActivity::class.java)
        startActivity(intent)
    }

}