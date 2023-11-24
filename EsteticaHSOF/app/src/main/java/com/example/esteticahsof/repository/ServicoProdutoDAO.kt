package com.example.esteticahsof.repository

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.esteticahsof.model.Produto
import com.example.esteticahsof.model.ServicoProduto

@Dao
interface ServicoProdutoDAO {

    @Insert
    fun salvar(servicoProduto: ServicoProduto) : Long

    @Update
    fun atualizar(servicoProduto: ServicoProduto)

    @Delete
    fun deletar(servicoProduto: ServicoProduto)

    @Query("SELECT * FROM servicos_produtos WHERE id_servico = :id")
    fun getProdutosPorServico(id: Int): List<ServicoProduto>

    @Query("SELECT * FROM servicos_produtos WHERE id_produto = :id")
    fun getServicosPorProduto(id: Int): List<ServicoProduto>

    @Query("SELECT * FROM servicos_produtos WHERE id_produto = :idProduto and id_servico = :idServico")
    fun getProdutoDoServico(idProduto: Int, idServico: Int): ServicoProduto

    @Query("SELECT * FROM servicos_produtos")
    fun getAll(): List<ServicoProduto>
}