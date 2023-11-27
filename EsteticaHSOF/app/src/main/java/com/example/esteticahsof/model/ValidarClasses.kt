package com.example.esteticahsof.model

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

    fun camposEmBrancoProdutoDeServico(idServico: Int, idProduto: Int, nomeProduto: String, qtdProduto: Float) : Boolean{
        return idServico < 0 || idProduto < 0 || nomeProduto.isEmpty() || qtdProduto < 0
    }

}