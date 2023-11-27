package com.example.esteticahsof.repository

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.esteticahsof.model.Servico

@Dao
interface ServicoDAO {

    @Insert
    fun salvar(servico: Servico) : Long

    @Update
    fun atualizar(servico: Servico)

    @Delete
    fun deletar(servico: Servico)

    @Query("SELECT * FROM servicos WHERE id = :id")
    fun getServico(id: Int): Servico

    @Query("SELECT * FROM servicos")
    fun getAll(): List<Servico>
}