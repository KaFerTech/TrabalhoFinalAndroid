package com.example.esteticahsof.repository

import android.content.Context
import com.example.esteticahsof.model.Agendamento

class AgendamentoRepository(context: Context) {

    val dao = AgendamentoDataBase.getInstance(context).getAgendamentoDAO()

    fun salvar(agendamento: Agendamento): Boolean {
        return dao.salvar(agendamento) > 0
    }

    fun atualizar(agendamento: Agendamento) {
        dao.atualizar(agendamento)
    }

    fun deletar(agendamento: Agendamento) {
        dao.deletar(agendamento)
    }

    fun getAgendamento(id: Int): Agendamento {
        return dao.getAgendamento(id)
    }

    fun getAll(): List<Agendamento> {
        return dao.getAll()
    }

    fun getAllByDate(date: String): List<Agendamento> {
        return dao.getAllByDate(date)
    }

}