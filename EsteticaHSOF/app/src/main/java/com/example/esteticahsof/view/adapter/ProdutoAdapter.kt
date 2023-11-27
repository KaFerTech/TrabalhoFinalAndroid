package com.example.esteticahsof.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.esteticahsof.R
import com.example.esteticahsof.model.Produto
import com.example.esteticahsof.view.viewholder.ProdutoViewHolder

class ProdutoAdapter(var context: Context): RecyclerView.Adapter<ProdutoViewHolder>() {

    lateinit var listaAdapter: List<Produto>
    lateinit var listaOriginal: List<Produto>
    var onItemClick : ((Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProdutoViewHolder {
        val layout = LayoutInflater.from(context)
            .inflate(R.layout.produto_layout, parent, false)
        return ProdutoViewHolder(layout)
    }

    override fun onBindViewHolder(holder: ProdutoViewHolder, position: Int) {
        val produto = listaAdapter[position]
        val txtHolder = "${produto.nomeProduto}    |     ${produto.qtdEstoque} Unidades em estoque"
        holder.txtDadosProduto.text = txtHolder

        holder.itemView.setOnClickListener {
            onItemClick?.invoke(position)
        }
    }

    override fun getItemCount(): Int {
        return listaAdapter.size
    }

    fun updateAdapter(list: List<Produto>){
        listaAdapter = list
        listaOriginal = list
        notifyDataSetChanged()
    }

    fun filter(query: String) {
        listaAdapter = if (query.isEmpty()) {
            listaOriginal
        } else {
            listaOriginal.filter { produto ->
                produto.nomeProduto.contains(query, ignoreCase = true)
            }
        }
        notifyDataSetChanged()
    }

}