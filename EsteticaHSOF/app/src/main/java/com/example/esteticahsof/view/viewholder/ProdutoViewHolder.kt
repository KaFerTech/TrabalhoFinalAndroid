package com.example.esteticahsof.view.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.esteticahsof.R

class ProdutoViewHolder(layout: View): RecyclerView.ViewHolder(layout) {

    var txtDadosProduto = layout.findViewById<TextView>(R.id.txtDadosProduto)

}