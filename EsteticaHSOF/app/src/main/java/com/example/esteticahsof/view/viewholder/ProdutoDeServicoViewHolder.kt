package com.example.esteticahsof.view.viewholder

import android.view.View
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import com.example.esteticahsof.R

class ProdutoDeServicoViewHolder(layout: View): RecyclerView.ViewHolder(layout) {

    var txtNomeProduto = layout.findViewById<CheckBox>(R.id.txtNomeProduto)

}