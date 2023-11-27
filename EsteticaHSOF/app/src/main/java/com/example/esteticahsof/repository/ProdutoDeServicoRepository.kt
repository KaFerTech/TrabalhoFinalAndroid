package com.example.esteticahsof.repository

import android.content.Context
import com.example.esteticahsof.model.ProdutoDeServico

class ProdutoDeServicoRepository(context: Context) {

    val dao = ProdutoDeServicoDataBase.getInstance(context).getProdutoDeServicoDAO()

    fun salvar(produtoDeServico: ProdutoDeServico): Boolean {
        return dao.salvar(produtoDeServico) > 0
    }

    fun deletar(produtoDeServico: ProdutoDeServico) {
        dao.deletar(produtoDeServico)
    }

    fun getProdutoDoServico(id: Int): ProdutoDeServico {
        return dao.getProdutoDeServico(id)
    }

    fun getAll() : List<ProdutoDeServico> {
        return dao.getAll()
    }
}