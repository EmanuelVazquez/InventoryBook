package com.example.inventorybook

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.inventorybook.Modelos.Paper

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
        holder.equipoUsuario.text = paper.Usuario
        Log.d("Usuario: ", "$paper")
    }


    inner class PaperViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var equipoTitulo: TextView = itemView.findViewById(R.id.cardTitulo)
        var equipoUsuario: TextView = itemView.findViewById(R.id.cardUsuario)

    }
}