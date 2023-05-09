package com.example.inventorybook


import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.inventorybook.Modelos.Paper
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder


class EquipoAdapter(var context: Context, var listPaper: MutableList<Paper>): RecyclerView.Adapter<EquipoAdapter.PaperViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaperViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.card_item_paper_view_holder, parent, false)
        return PaperViewHolder(view)
    }

    override fun getItemCount() = listPaper.size

    override fun onBindViewHolder(holder: PaperViewHolder, position: Int) {
        val paper = listPaper[position]
        holder.equipoTitulo.text = paper.Titulo
        holder.equipoUsuario.text = paper.User
        holder.itemView.setOnClickListener{
            val intent = Intent(context,NewPaperActivity::class.java)
            intent.putExtra("Id",paper.Id)
            startActivity(context,intent,null)
        }
        holder.qr_buttn.setOnClickListener{
            showQrDialog(paper.Id)

        }
    }


    inner class PaperViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var equipoTitulo: TextView = itemView.findViewById(R.id.cardTitulo)
        var equipoUsuario: TextView = itemView.findViewById(R.id.cardUsuario)
        var qr_buttn: ImageButton = itemView.findViewById(R.id.Qr_Button)

    }

    private fun showQrDialog(id: String){
        val dialog = Dialog(context)
        dialog.setContentView(R.layout.dialog_qr)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val qrImage: ImageView = dialog.findViewById(R.id.ivQRCode)
        try {
            var bancoderEncoder: BarcodeEncoder = BarcodeEncoder()
            var bitmat: Bitmap = bancoderEncoder.encodeBitmap(
                id,
                BarcodeFormat.QR_CODE,
                250,
                250
            )
            qrImage.setImageBitmap(bitmat)
        }catch (e: java.lang.Exception){

        }

        dialog.show()

    }
}