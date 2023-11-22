package com.example.esteticahsof.repository

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.esteticahsof.model.Cliente

@Dao
interface ClienteDAO {

    @Insert
    fun salvar(cliente: Cliente) : Long

    @Update
    fun atualizar(cliente: Cliente)

    @Delete
    fun deletar(cliente: Cliente)

    @Query("SELECT * FROM clientes WHERE id = :id")
    fun getCliente(id: Int): Cliente

    @Query("SELECT * FROM clientes")
    fun getAll() : List<Cliente>
}