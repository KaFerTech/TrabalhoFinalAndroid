package com.example.esteticahsof.model

class ValidarCliente {
    fun camposEmBranco(nomeCliente : String, telefone: String) : Boolean{
        return nomeCliente.isEmpty() || telefone.isEmpty()
    }
}