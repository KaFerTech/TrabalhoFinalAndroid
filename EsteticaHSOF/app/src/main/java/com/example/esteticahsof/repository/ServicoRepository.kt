package com.example.esteticahsof.repository

import android.content.Context
import com.example.esteticahsof.model.Servico

class ServicoRepository(context: Context) {

    val dao = ServicoDataBase.getInstance(context).getDAO()

    fun salvar(servico: Servico) : Boolean {
        return dao.salvar(servico) > 0
    }

    fun atualizar(servico: Servico) {
        dao.atualizar(servico)
    }

    fun deletar(servico: Servico) {
        dao.deletar(servico)
    }

    fun getServico(id: Int): Servico {
        return dao.getServico(id)
    }

    fun getAll(): List<Servico> {
        return dao.getAll()
    }

}