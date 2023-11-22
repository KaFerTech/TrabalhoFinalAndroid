package com.example.esteticahsof.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.esteticahsof.model.Cliente
import com.example.esteticahsof.model.ValidarCliente
import com.example.esteticahsof.repository.ClienteRepository

class ClienteViewModel(application: Application): AndroidViewModel(application) {

    private var repository = ClienteRepository(application.applicationContext)
    private var validacao = ValidarCliente()
    private var clienteFromDB = MutableLiveData<Cliente>()
    private var txtToast = MutableLiveData<String>()
    private var listViewModel = MutableLiveData<List<Cliente>>()

    fun getListViewModel() : LiveData<List<Cliente>> {
        return listViewModel
    }

    fun getListFromDB() {
        listViewModel.value = repository.getAll()
    }

    fun getClienteFromDB(): LiveData<Cliente> {
        return clienteFromDB
    }

    fun getTxtToast(): LiveData<String> {
        return txtToast
    }

    fun findCliente(id: Int) {
        clienteFromDB.value = repository.getCliente(id)
    }

    fun validarAntesDeAtualizar(nomeCliente: String, telefone: String) : Boolean {
        if (validacao.camposEmBranco(nomeCliente, telefone)) {
            txtToast.value = "Preencha Pelomenos Nome e CPF"
            return false
        }
        return true
    }

    fun atualizar(cliente: Cliente) {
        repository.atualizar(cliente)
        txtToast.value = "Cliente atualizado!"
    }

}