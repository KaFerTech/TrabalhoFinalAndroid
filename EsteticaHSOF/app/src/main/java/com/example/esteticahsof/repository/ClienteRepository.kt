package com.example.esteticahsof.repository

import android.content.Context
import com.example.esteticahsof.model.Cliente

class ClienteRepository(context: Context) {

    val dao = ClienteDataBase.getInstance(context).getClienteDAO()

    fun salvar(cliente: Cliente) : Boolean {
        return dao.salvar(cliente) > 0
    }

    fun atualizar(cliente: Cliente) {
        dao.atualizar(cliente)
    }

    fun deletar(cliente: Cliente) {
        dao.deletar(cliente)
    }

    fun getCliente(id: Int): Cliente {
        return dao.getCliente(id)
    }

    fun getAll() : List<Cliente> {
        return dao.getAll()
    }

}