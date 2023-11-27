package com.example.esteticahsof.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "servicos")
data class Servico (
    @ColumnInfo @PrimaryKey(autoGenerate = true) var id: Int,
    @ColumnInfo(name = "nome_servico") var nomeServico: String,
    @ColumnInfo(name = "preco") var preco: Float,
    @ColumnInfo(name = "duracao") var duracao: Int
)