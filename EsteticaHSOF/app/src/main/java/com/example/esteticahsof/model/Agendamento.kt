package com.example.esteticahsof.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "agendamentos")
data class Agendamento(
    @ColumnInfo @PrimaryKey(autoGenerate = true) var id: Int,
    @ColumnInfo(name = "id_cliente") var idCliente: Int,
    @ColumnInfo(name = "id_servico") var idServico: Int,
    @ColumnInfo(name = "data_hora") var dataHora: Date,
    @ColumnInfo(name = "observacao") var observacao: String
)