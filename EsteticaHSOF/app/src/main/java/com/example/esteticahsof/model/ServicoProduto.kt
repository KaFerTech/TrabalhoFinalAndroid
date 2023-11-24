package com.example.esteticahsof.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

//
//@Entity(
//    tableName = "servicos_produtos",
//    primaryKeys = ["servicoId", "produtoId"],
//    foreignKeys = [
//        ForeignKey(
//            entity = Servico::class,
//            parentColumns = ["id"],
//            childColumns = ["servicoId"]
//        ),
//        ForeignKey(
//            entity = Produto::class,
//            parentColumns = ["id"],
//            childColumns = ["produtoId"]
//        )
//    ]
//)
//data class ServicoProduto(
//    val idServico: Int,
//    val idProduto: Int,
//    val qtd_produto: Float
////    @ColumnInfo(name = "id_servico") val idServico: Int,
////    @ColumnInfo(name = "id_produto") val idProduto: Int,
////    @ColumnInfo(name = "qtd_produto") val qtd_produto: Float
//)

@Entity(tableName = "servicos_produtos")
data class ServicoProduto (
    @ColumnInfo @PrimaryKey(autoGenerate = true) var id: Int,
    @ColumnInfo(name = "id_servico") val idServico: Int,
    @ColumnInfo(name = "id_produto") val idProduto: Int,
    @ColumnInfo(name = "qtd_produto") val qtdProduto: Float
)