package com.example.esteticahsof.view.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.esteticahsof.R

class AgendamentoViewHolder(layout: View): RecyclerView.ViewHolder(layout) {

    var horaAgendamento = layout.findViewById<TextView>(R.id.horaAgendamento)
    var clienteAgendamento = layout.findViewById<TextView>(R.id.clienteAgendamento)
    var servicoAgendamento = layout.findViewById<TextView>(R.id.servicoAgendamento)
    var precoAgendamento = layout.findViewById<TextView>(R.id.precoAgendamento)
    var horaTerminoAgendamento = layout.findViewById<TextView>(R.id.horaTerminoAgendamento)
    var observacaoAgendamento = layout.findViewById<TextView>(R.id.observacaoAgendamento)

}