package com.example.esteticahsof.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.esteticahsof.R
import com.example.esteticahsof.model.Cliente
import com.example.esteticahsof.view.viewholder.ClienteViewHolder

class ClienteAdapter(var context: Context): RecyclerView.Adapter<ClienteViewHolder>() {

    lateinit var listaAdapter: List<Cliente>
    var onItemClick : ((Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClienteViewHolder {
        val layout = LayoutInflater.from(context)
            .inflate(R.layout.cliente_layout, parent, false)
        return ClienteViewHolder(layout)
    }

    override fun onBindViewHolder(holder: ClienteViewHolder, position: Int) {
        val cliente = listaAdapter[position]
        val txtHolder = "${cliente.nomeCliente}"
        holder.txtDadosCliente.text = txtHolder

        holder.itemView.setOnClickListener {
            onItemClick?.invoke(position)
        }
    }

    override fun getItemCount(): Int {
        return listaAdapter.size
    }

    fun updateAdapter(list: List<Cliente>) {
        listaAdapter = list
        notifyDataSetChanged()
    }

}