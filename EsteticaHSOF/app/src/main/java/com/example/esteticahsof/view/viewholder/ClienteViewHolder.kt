package com.example.esteticahsof.view.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.esteticahsof.R

class ClienteViewHolder(layout: View): RecyclerView.ViewHolder(layout) {

    var txtDadosCliente = layout.findViewById<TextView>(R.id.txtDadosCliente)

}