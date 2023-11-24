package com.example.esteticahsof.view.adapter

import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.esteticahsof.R
import com.example.esteticahsof.model.Produto
import com.example.esteticahsof.model.ServicoProduto
import com.example.esteticahsof.view.viewholder.ServicoProdutoViewHolder

//class ServicoProdutoAdapter(var context: Context): RecyclerView.Adapter<ServicoProdutoViewHolder>() {


class ServicoProdutoAdapter(var context: Context, private val onProdutoCheckedChanged: (Produto, Boolean) -> Unit) : RecyclerView.Adapter<ServicoProdutoViewHolder>() {



    lateinit var listaAdapter: List<Produto>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServicoProdutoViewHolder {
        var layout = LayoutInflater.from(context)
            .inflate(R.layout.adicionar_produtos_layout, parent, false)
        return ServicoProdutoViewHolder(layout)
    }

    override fun onBindViewHolder(holder: ServicoProdutoViewHolder, position: Int) {
        val servicoProduto = listaAdapter[position]
        val checkBoxHolder = servicoProduto

        holder.checkBoxServicoProduto.text = checkBoxHolder.nomeProduto


        holder.checkBoxServicoProduto.setOnCheckedChangeListener { _, isChecked ->
            // Avisa o callback que o estado do CheckBox mudou

        }
    }

    override fun getItemCount(): Int {
        return listaAdapter.size
    }

    fun updateAdapter(list: List<Produto>) {
        listaAdapter = list
        notifyDataSetChanged()
    }
}