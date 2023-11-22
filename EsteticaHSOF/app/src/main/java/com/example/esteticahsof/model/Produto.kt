package com.example.esteticahsof.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Currency

@Entity(tableName = "produtos")
data class Produto(
    @ColumnInfo @PrimaryKey(autoGenerate = true) var id: Int,
    @ColumnInfo(name = "descricao_produto") var descricaoProduto: String,
    @ColumnInfo(name = "preco") var preco: Currency,
    @ColumnInfo(name = "qtd_estoque") var qtdEstoque: Double
)