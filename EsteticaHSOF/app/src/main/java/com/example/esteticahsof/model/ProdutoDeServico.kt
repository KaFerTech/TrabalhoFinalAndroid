package com.example.esteticahsof.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "produtos_de_servicos")
data class ProdutoDeServico (
    @ColumnInfo @PrimaryKey(autoGenerate = true) var id: Int,
    @ColumnInfo(name = "id_servico") val idServico: Int,
    @ColumnInfo(name = "id_produto") val idProduto: Int,
    @ColumnInfo(name = "nome_produto") val nomeProduto: String,
    @ColumnInfo(name = "qtd_produto") val qtdProduto: Float
)