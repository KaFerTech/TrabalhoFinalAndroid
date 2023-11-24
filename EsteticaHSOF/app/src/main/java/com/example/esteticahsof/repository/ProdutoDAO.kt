package com.example.esteticahsof.repository

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.esteticahsof.model.Produto

@Dao
interface ProdutoDAO {

    @Insert
    fun salvar(produto: Produto) : Long

    @Update
    fun atualizar(produto: Produto)

    @Delete
    fun deletar(produto: Produto)

    @Query("SELECT * FROM produtos WHERE id = :id")
    fun getProduto(id: Int): Produto

    @Query("SELECT * FROM produtos")
    fun getAll(): List<Produto>
}