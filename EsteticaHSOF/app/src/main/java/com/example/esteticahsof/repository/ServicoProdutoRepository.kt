package com.example.esteticahsof.repository

import android.content.Context
import com.example.esteticahsof.model.ServicoProduto

class ServicoProdutoRepository(context: Context) {

    val dao = ServicoProdutoDataBase.getInstance(context).getServicoProdutoDAO()

    fun salvar(servicoProduto: ServicoProduto): Boolean {
        return dao.salvar(servicoProduto) > 0
    }

    fun atualizar(servicoProduto: ServicoProduto) {
        dao.deletar(servicoProduto)
    }

    fun getProdutoDoServico(idProduto: Int, idServico: Int): ServicoProduto {
        return dao.getProdutoDoServico(idProduto, idServico)
    }

    fun getProdutosPorServico(idServico: Int): List<ServicoProduto> {
        return dao.getProdutosPorServico(idServico)
    }

    fun getServicosPorProduto(idProduto: Int): List<ServicoProduto> {
        return dao.getServicosPorProduto(idProduto)
    }

    fun getAll() : List<ServicoProduto> {
        return dao.getAll()
    }

}