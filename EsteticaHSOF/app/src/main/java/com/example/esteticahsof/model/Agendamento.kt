package com.example.esteticahsof.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "agendamentos")
data class Agendamento(
    @ColumnInfo @PrimaryKey(autoGenerate = true) var id: Int,
    @ColumnInfo(name = "cliente") var cliente: String,
    @ColumnInfo(name = "servico") var servico: String,
    @ColumnInfo(name = "preco") var preco: Float,
    @ColumnInfo(name = "duracao") var duracao: Int,
    @ColumnInfo(name = "data") var data: String,
    @ColumnInfo(name = "hora") var hora: String,
    @ColumnInfo(name = "observacao") var observacao: String
)