package com.example.esteticahsof.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.esteticahsof.R
import com.example.esteticahsof.model.Agendamento
import com.example.esteticahsof.view.viewholder.AgendamentoViewHolder
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

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

        holder.clienteAgendamento.text = agendamento.cliente
        holder.servicoAgendamento.text = agendamento.servico
        holder.precoAgendamento.text = agendamento.preco.toString()
        holder.horaAgendamento.text = agendamento.hora
        val horaTermino = calcularHoraTermino(agendamento.hora, agendamento.duracao)
        holder.horaTerminoAgendamento.text = horaTermino
        holder.observacaoAgendamento.text = agendamento.observacao

        holder.itemView.setOnClickListener {
            onItemClick?.invoke(position)
        }
    }

    override fun getItemCount(): Int {
        return listaAdapter.size
    }

    fun updateAdapter(list: List<Agendamento>){
        listaAdapter = list
        listaAdapter = list.sortedBy { it.hora }
        notifyDataSetChanged()
    }

    private fun calcularHoraTermino(horaInicial: String, duracao: Int): String {
        val sdf = SimpleDateFormat("HH:mm", Locale.getDefault())

        // Converter a hora inicial para um objeto Date
        val dataHoraInicial = sdf.parse(horaInicial)

        // Adicionar a duração em minutos à data e hora inicial
        val calendar = Calendar.getInstance()
        calendar.time = dataHoraInicial
        calendar.add(Calendar.MINUTE, duracao)

        // Formatar a nova hora
        return sdf.format(calendar.time)
    }

}