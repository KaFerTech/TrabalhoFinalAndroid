package com.example.esteticahsof.view.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.esteticahsof.R

class ServicoViewHolder(layout: View): RecyclerView.ViewHolder(layout) {

    var txtDadosServico = layout.findViewById<TextView>(R.id.txtNomeServico)

}