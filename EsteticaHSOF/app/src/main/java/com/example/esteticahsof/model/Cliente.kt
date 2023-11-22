package com.example.esteticahsof.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "clientes")
data class Cliente (
    @ColumnInfo @PrimaryKey(autoGenerate = true) var id: Int,
    @ColumnInfo(name = "nome_cliente") var nomeCliente: String,
    @ColumnInfo(name = "telefone") var telefone: String,
    @ColumnInfo(name = "email") var email: String,
    @ColumnInfo(name = "cpf") var cpf: String,
    @ColumnInfo(name = "data_nascimento") var dataNascimento: String,
    @ColumnInfo(name = "genero") var genero: Int,
    @ColumnInfo(name = "endereco") var endereco: String,
    @ColumnInfo(name = "alergias") var alergias: String,
    @ColumnInfo(name = "observacoes") var observacoes: String
)