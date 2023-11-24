package com.example.esteticahsof.view.viewholder

import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.esteticahsof.R

class ServicoProdutoViewHolder(layout: View): RecyclerView.ViewHolder(layout) {

    var checkBoxServicoProduto = layout.findViewById<CheckBox>(R.id.checkBoxProdutoServico)
    var edtQuantidadeProduto = layout.findViewById<EditText>(R.id.edtQuantidadeProduto)

}