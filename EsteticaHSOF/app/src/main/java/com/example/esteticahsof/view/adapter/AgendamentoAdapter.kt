package com.example.esteticahsof.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.esteticahsof.R
import com.example.esteticahsof.model.Agendamento
import com.example.esteticahsof.view.viewholder.AgendamentoViewHolder
import com.example.esteticahsof.viewmodel.ClienteViewModel

class AgendamentoAdapter(var context: Context): RecyclerView.Adapter<AgendamentoViewHolder>() {

    lateinit var listaAdapter: List<Agendamento>
    var onItemClick : ((Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AgendamentoViewHolder {
        var layout = LayoutInflater.from(context)
            .inflate(R.layout.agendamento_layout, parent, false)
        return AgendamentoViewHolder(layout)
    }

    override fun onBindViewHolder(holder: AgendamentoViewHolder, position: Int) {
        val agendamento = listaAdapter[position]
        val txtHolder = agendamento

        holder.clienteAgendamento.text = txtHolder.cliente
        holder.servicoAgendamento.text = txtHolder.servico
        holder.horaAgendamento.text = txtHolder.hora
        holder.precoAgendamento.text = txtHolder.preco.toString()
        holder.observacaoAgendamento.text = txtHolder.observacao


        holder.itemView.setOnClickListener {
            onItemClick?.invoke(position)
        }
    }

    override fun getItemCount(): Int {
        return listaAdapter.size
    }

    fun updateAdapter(list: List<Agendamento>){
        listaAdapter = list
        notifyDataSetChanged()
    }
}