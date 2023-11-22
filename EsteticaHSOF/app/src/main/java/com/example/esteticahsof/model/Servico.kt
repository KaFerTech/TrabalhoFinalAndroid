package com.example.esteticahsof.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Currency

@Entity(tableName = "servicos")
data class Servico (
    @ColumnInfo @PrimaryKey(autoGenerate = true) var id: Int,
    @ColumnInfo(name = "descricao_servico") var descricaoServico: String,
    @ColumnInfo(name = "preco") var preco: Currency,
    @ColumnInfo(name = "duracao") var duracao: Int,
)