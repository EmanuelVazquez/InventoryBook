package com.example.inventorybook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import com.example.inventorybook.Modelos.Paper
import com.example.inventorybook.Repository.PaperRepository
import java.util.*

class NewPaperActivity : AppCompatActivity() {

    lateinit var encabezadoText: TextView
    lateinit var tituloText: EditText
    lateinit var equipoText: EditText
    lateinit var modeloText: EditText
    lateinit var serialText: EditText
    lateinit var facturaText: EditText
    lateinit var fechaText: EditText
    lateinit var windowsKey: EditText
    lateinit var usuarioText: EditText
    lateinit var dptoText: EditText
    lateinit var confirmButton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_paper)
        encabezadoText = findViewById(R.id.textView3)
        tituloText = findViewById(R.id.inputTitulo)
        equipoText = findViewById(R.id.inputEquipo)
        modeloText = findViewById(R.id.inputModelo)
        serialText = findViewById(R.id.inputSerial)
        facturaText = findViewById(R.id.inputFactura)
        fechaText = findViewById(R.id.inputFecha)
        windowsKey = findViewById(R.id.inputClave)
        usuarioText = findViewById(R.id.inputUsuario)
        dptoText = findViewById(R.id.inputDpto)
        confirmButton = findViewById(R.id.confirmNewPaperButton)
        val id = intent.getStringExtra("Id")
        if(id.isNullOrEmpty()){
           setupNew()
        }else{
            PaperRepository().getById(id).addOnSuccessListener {
                if(it.exists()){
                    val paper = it.toObject(Paper::class.java)
                    if (paper != null) {
                        setupDetail(paper)
                    }
                }
            }
        }

    }
    private fun setupNew(){
        encabezadoText.text = "Nueva Hoja"

        confirmButton.setOnClickListener(){
            if(tituloText.text.toString().isNotEmpty() &&
                equipoText.text.toString().isNotEmpty() &&
                modeloText.text.toString().isNotEmpty() &&
                serialText.text.toString().isNotEmpty() &&
                facturaText.text.toString().isNotEmpty() &&
                fechaText.text.toString().isNotEmpty() &&
                windowsKey.text.toString().isNotEmpty() &&
                usuarioText.text.toString().isNotEmpty() &&
                dptoText.text.toString().isNotEmpty()){

                val paper = Paper(UUID.randomUUID().toString(),
                    tituloText.text.toString(),
                    equipoText.text.toString(),
                    modeloText.text.toString(),
                    serialText.text.toString(),
                    fechaText.text.toString(),
                    facturaText.text.toString(),
                    windowsKey.text.toString(),
                    usuarioText.text.toString(),
                    dptoText.text.toString(),
                )

                val paperRepository = PaperRepository()
                paperRepository.add(paper, this)
                finish()

            }
        }

    }
    private fun setupDetail(paper: Paper){
        encabezadoText.text = "Detalle"
        confirmButton.visibility = View.GONE

        tituloText.setText(paper.Titulo)
        equipoText.setText(paper.Equipo)
        modeloText.setText(paper.Modelo)
        serialText.setText(paper.Serial)
        fechaText.setText(paper.Fecha)
        facturaText.setText(paper.Factura)
        windowsKey.setText(paper.WindowsKey)
        usuarioText.setText(paper.User)
        dptoText.setText(paper.Departamento)


    }

}