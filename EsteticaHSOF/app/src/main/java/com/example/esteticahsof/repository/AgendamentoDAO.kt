package com.example.esteticahsof.repository

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.esteticahsof.model.Agendamento

@Dao
interface AgendamentoDAO {
    @Insert
    fun salvar(agendamento: Agendamento): Long

    @Update
    fun atualizar(agendamento: Agendamento)

    @Delete
    fun deletar(agendamento: Agendamento)

    @Query("SELECT * FROM agendamentos WHERE id = :id")
    fun getAgendamento(id: Int): Agendamento

    @Query("SELECT * FROM agendamentos")
    fun getAll() : List<Agendamento>
}