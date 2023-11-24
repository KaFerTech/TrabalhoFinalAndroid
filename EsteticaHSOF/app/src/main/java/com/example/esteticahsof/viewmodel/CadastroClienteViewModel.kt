package com.example.esteticahsof.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.esteticahsof.model.Cliente
import com.example.esteticahsof.model.ValidarClasses
import com.example.esteticahsof.repository.ClienteRepository

class CadastroClienteViewModel(application: Application): AndroidViewModel(application) {

    private var repository = ClienteRepository(application.applicationContext)
    private var validacao = ValidarClasses()
    private var txtToast = MutableLiveData<String>()

    fun getTxtToast() : LiveData<String> {
        return txtToast
    }

    fun salvar(nomeCliente: String, telefone: String, email: String, cpf: String,
               dtNascimento: String, genero: Int, endereco: String, alergias: String,
               observacoes: String): Boolean{

        if (validacao.camposEmBrancoCliente(nomeCliente, telefone)) {
            txtToast.value = "Preencha Pelomenos Nome e CPF"
            return false
        }

        var cliente = Cliente(0, nomeCliente, telefone, email, cpf, dtNascimento,
            genero, endereco, alergias, observacoes
        )

        if (!repository.salvar(cliente)) {
            txtToast.value = "Erro ao salvar..."
            return false
        }

        txtToast.value = "Cliente Salvo!"
        return true
    }

}