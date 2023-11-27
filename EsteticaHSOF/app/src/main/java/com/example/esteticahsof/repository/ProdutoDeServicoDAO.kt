package com.example.esteticahsof.repository

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.esteticahsof.model.ProdutoDeServico

@Dao
interface ProdutoDeServicoDAO {

    @Insert
    fun salvar(produtoDeServico: ProdutoDeServico) : Long

    @Update
    fun atualizar(produtoDeServico: ProdutoDeServico)

    @Delete
    fun deletar(produtoDeServico: ProdutoDeServico)

    @Query("SELECT * FROM produtos_de_servicos WHERE id = :id")
    fun getProdutoDeServico(id: Int): ProdutoDeServico

    @Query("SELECT * FROM produtos_de_servicos")
    fun getAll(): List<ProdutoDeServico>

    @Query("SELECT * FROM produtos_de_servicos WHERE id_servico = :idServico")
    fun getListProdutosDeServico(idServico: Int): List<ProdutoDeServico>
}