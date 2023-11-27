package com.example.esteticahsof.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.esteticahsof.R
import com.example.esteticahsof.model.ProdutoDeServico
import com.example.esteticahsof.view.viewholder.ProdutoDeServicoViewHolder

class ServicoProdutoAdapter(var context: Context): RecyclerView.Adapter<ProdutoDeServicoViewHolder>() {

    lateinit var listaAdapter: List<ProdutoDeServico>
    var onItemClick : ((Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProdutoDeServicoViewHolder {
        var layout = LayoutInflater.from(context)
            .inflate(R.layout.servico_produto_layout, parent, false)
        return ProdutoDeServicoViewHolder(layout)
    }

    override fun onBindViewHolder(holder: ProdutoDeServicoViewHolder, position: Int) {
        val servicoProduto = listaAdapter[position]
        val txtHolder = "${servicoProduto.idProduto} |"

        holder.txtNomeProduto.text = txtHolder

        holder.itemView.setOnClickListener {
            onItemClick?.invoke(position)
        }
    }

    override fun getItemCount(): Int {
        return listaAdapter.size
    }

    fun updateAdapter(list: List<ProdutoDeServico>) {
        listaAdapter = list
        notifyDataSetChanged()
    }
}