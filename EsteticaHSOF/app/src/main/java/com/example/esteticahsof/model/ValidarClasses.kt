package com.example.esteticahsof.model

import java.sql.Time
import java.util.Date

class ValidarClasses {
    fun camposEmBrancoCliente(nomeCliente : String, telefone: String) : Boolean{
        return nomeCliente.isEmpty() || telefone.isEmpty()
    }
    fun camposEmBrancoProduto(nomeProduto : String) : Boolean{
        return nomeProduto.isEmpty()
    }
    fun camposEmBrancoServico(descricaoServico : String) : Boolean{
        return descricaoServico.isEmpty()
    }

    fun camposEmBrancoAgendamento(cliente: String, servico: String, data: String, hora: String) : Boolean{
        return cliente.isEmpty() || servico.isEmpty() || data.isEmpty() || hora.isEmpty()
    }
}