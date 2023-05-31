package com.example.inventorybook

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import com.example.inventorybook.Modelos.HistoryLog
import com.example.inventorybook.Modelos.Paper
import com.example.inventorybook.Repository.HistoryLogRepository
import com.example.inventorybook.Repository.PaperRepository
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.text.SimpleDateFormat
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
    lateinit var historyListBtn: ImageButton
    lateinit var editButton: ImageButton
    lateinit var addBtn: ImageButton
    lateinit var calendar: Calendar
    lateinit var dialogAddMtn: Dialog
    lateinit var picker: DatePickerDialog
    lateinit var options: LinearLayout

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
        historyListBtn = findViewById(R.id.historyBtn)
        addBtn = findViewById(R.id.addBtn)
        editButton = findViewById(R.id.editBtn)
        options = findViewById(R.id.optionsLayout)
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
        calendar = Calendar.getInstance()
        picker = DatePickerDialog(this, { view, year, month, dayOfTheMonth ->
            calendar.set(Calendar.YEAR,year)
            calendar.set(Calendar.MONTH,month)
            calendar.set(Calendar.DAY_OF_MONTH,dayOfTheMonth)
            fechaText.setText(SimpleDateFormat("dd/mm/yyyy").format(calendar.time))
        }, calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH))
    }
    private fun setupNew(){
        encabezadoText.text = "Nueva Hoja"


        fechaText.setOnClickListener(){
            picker.show()
        }
        confirmButton.setOnClickListener {
            save("")
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
        setFocusable(false)

        val historyLogRepository = HistoryLogRepository()
        historyLogRepository.getByPaperId(paper.Id).addOnSuccessListener { spanshot ->
            var historyLogs: MutableList<HistoryLog> = mutableListOf()
            for (document in spanshot.documents) {
                val historyLog: HistoryLog? = document.toObject(HistoryLog::class.java)
                if (historyLog != null) {
                    historyLogs.add(historyLog)
                }
            }
            options.visibility = View.VISIBLE

            val arreglo: Array<String> = historyLogs.map { "${it.Fecha}: ${it.Usuario}" }.toTypedArray()
            historyListBtn.setOnClickListener(){
                MaterialAlertDialogBuilder(this)
                    .setTitle("Historial de Mantenimiento")
                    .setItems(arreglo){_, which ->
                        when(which){

                        }
                    }.show()
            }
        }


        addBtn.setOnClickListener(){
            showDialog(paper.Id)
        }
        editButton.setOnClickListener(){
            setFocusable(true)
            confirmButton.visibility = View.VISIBLE
        }

        confirmButton.setOnClickListener {
            save(paper.Id)
        }


    }



    private fun setFocusable(boolean: Boolean){
        tituloText.isFocusable = boolean
        equipoText.isFocusable = boolean
        modeloText.isFocusable = boolean
        serialText.isFocusable = boolean
        fechaText.isFocusable = boolean
        facturaText.isFocusable = boolean
        windowsKey.isFocusable = boolean
        usuarioText.isFocusable = boolean
        dptoText.isFocusable = boolean
    }

    private fun save(id: String){

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
            if (!id.isNullOrEmpty()){
                paper.Id = id
            }


            val paperRepository = PaperRepository()
            paperRepository.add(paper, this)
            finish()
        }

    }

    private fun showDialog(PaperId:String){
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.car_item_history_view_holder)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val fecha: EditText = dialog.findViewById(R.id.fechaInputMnt)
        val usuario: EditText = dialog.findViewById(R.id.usuarioMtn)
        val btn: Button = dialog.findViewById(R.id.btnAddMtn)

        fecha.setOnClickListener(){
            DatePickerDialog(this, { view, year, month, dayOfTheMonth ->
                calendar.set(Calendar.YEAR,year)
                calendar.set(Calendar.MONTH,month)
                calendar.set(Calendar.DAY_OF_MONTH,dayOfTheMonth)
                fecha.setText(SimpleDateFormat("dd/mm/yyyy").format(calendar.time))
            }, calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show()
        }

        btn.setOnClickListener(){
            if(fecha.text.isNotEmpty() && usuario.text.isNotEmpty()){
                val historyRepository = HistoryLogRepository()
                val historylog = HistoryLog(UUID.randomUUID().toString()
                ,PaperId,fecha.text.toString(),usuario.text.toString())
                historyRepository.add(historylog,this)
                dialog.dismiss()
            }

        }
        dialog.show()
    }

}