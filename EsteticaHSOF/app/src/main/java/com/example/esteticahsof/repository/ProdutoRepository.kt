package com.example.esteticahsof.repository

import android.content.Context
import com.example.esteticahsof.model.Produto

class ProdutoRepository(context: Context) {

    val dao = ProdutoDataBase.getInstance(context).getDAO()

    fun salvar(produto: Produto) : Boolean {
        return dao.salvar(produto) > 0
    }

    fun atualizar(produto: Produto) {
        dao.atualizar(produto)
    }

    fun deletar(produto: Produto) {
        dao.deletar(produto)
    }

    fun getProduto(id: Int): Produto {
        return dao.getProduto(id)
    }

    fun getAll(): List<Produto> {
        return dao.getAll()
    }

}