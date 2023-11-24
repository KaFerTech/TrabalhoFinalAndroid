package com.example.esteticahsof.model

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

    fun camposEmBrancoAgendamento(idCliente: Int, idServico: Int, dataHora: Date) : Boolean{
        return idCliente < 1 || idServico < 1 || dataHora == null
//                || dataHora.before(Date())
    }
}