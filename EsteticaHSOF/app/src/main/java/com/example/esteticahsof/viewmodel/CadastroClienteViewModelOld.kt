package com.example.esteticahsof.viewmodel
class CadastroClienteViewModelOld{
}


//
//import android.app.Application
//import androidx.lifecycle.AndroidViewModel
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import com.example.esteticahsof.model.Cliente
//import com.example.esteticahsof.model.ValidarCliente
//import com.example.esteticahsof.repository.ClienteRepository
//
//class CadastroClienteViewModelOld(application: Application): AndroidViewModel(application) {
//
//    private var repository = ClienteRepository(application.applicationContext)
//    private var listViewModel = MutableLiveData<List<Cliente>>()
//    private var validacao = ValidarCliente()
//    private var txtToast = MutableLiveData<String>()
//    private var clienteFromDB = MutableLiveData<Cliente>()
//
//    fun getListViewModel() : LiveData<List<Cliente>> {
//        return listViewModel
//    }
//
//    fun getListFromDB() {
//        listViewModel.value = repository.getAll()
//    }
//
//    fun getClienteFromDB() : LiveData<Cliente>{
//        return clienteFromDB
//    }
//
//    fun findCliente(id: Int) {
//        clienteFromDB.value = repository.getCliente(id)
//    }
//
//    fun getTxtToast() : LiveData<String> {
//        return txtToast
//    }
//
//    fun validarAntesDeAtualizar(nomeCliente: String, telefone: String) : Boolean {
//        if (validacao.camposEmBranco(nomeCliente, telefone)) {
//            txtToast.value = "Preencha Pelomenos Nome e CPF"
//            return false
//        }
//        return true
//    }
//
//    fun atualizar(cliente: Cliente) {
//        repository.atualizar(cliente)
//        txtToast.value = "Cliente atualizado!"
//    }
//
//    fun salvar(nomeCliente: String, telefone: String, email: String, cpf: String,
//               dtNascimento: String, genero: Int, endereco: String, alergias: String,
//               observacoes: String) : Boolean {
//
//        if (validacao.camposEmBranco(nomeCliente, telefone)) {
//            txtToast.value = "Preencha Pelomenos Nome e CPF"
//            return false
//        }
//
//        var cliente = Cliente(0, nomeCliente, telefone, email, cpf, dtNascimento,
//            genero, endereco, alergias, observacoes
//        )
//
//        if (!repository.salvar(cliente)) {
//            txtToast.value = "Erro ao salvar..."
//            return false
//        }
//
//        txtToast.value = "Cliente Salvo!"
//        return true
//    }
//}