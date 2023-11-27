package com.example.esteticahsof.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.esteticahsof.R
import com.example.esteticahsof.model.Servico
import com.example.esteticahsof.view.viewholder.ServicoViewHolder

class ServicoAdapter(var context: Context): RecyclerView.Adapter<ServicoViewHolder>() {

    lateinit var listaAdapter: List<Servico>
    lateinit var listaOriginal: List<Servico>
    var onItemClick : ((Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServicoViewHolder {
        var layout = LayoutInflater.from(context)
            .inflate(R.layout.servico_layout, parent, false)
        return ServicoViewHolder(layout)
    }

    override fun onBindViewHolder(holder: ServicoViewHolder, position: Int) {
        val servico = listaAdapter[position]
        val txtHolder = "${servico.nomeServico}"
        holder.txtDadosServico.text = txtHolder

        holder.itemView.setOnClickListener {
            onItemClick?.invoke(position)
        }
    }

    override fun getItemCount(): Int {
        return listaAdapter.size
    }

    fun updateAdapter(list: List<Servico>) {
        listaAdapter = list
        listaOriginal = list
        notifyDataSetChanged()
    }

    fun filter(query: String) {
        listaAdapter = if (query.isEmpty()) {
            listaOriginal
        } else {
            listaOriginal.filter { servico ->
                servico.nomeServico.contains(query, ignoreCase = true)
            }
        }
        notifyDataSetChanged()
    }

}