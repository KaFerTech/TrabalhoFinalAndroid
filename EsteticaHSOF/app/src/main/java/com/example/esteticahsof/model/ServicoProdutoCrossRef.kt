package com.example.esteticahsof.model

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "servico_produto",
    primaryKeys = ["servicoId", "produtoId"],
    foreignKeys = [
        ForeignKey(
            entity = Servico::class,
            parentColumns = ["id"],
            childColumns = ["servicoId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Produto::class,
            parentColumns = ["id"],
            childColumns = ["produtoId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class ServicoProdutoCrossRef(
    val servicoId: Int,
    val produtoId: Int
)