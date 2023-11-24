package com.example.esteticahsof.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.esteticahsof.model.Servico
import com.example.esteticahsof.model.ValidarClasses
import com.example.esteticahsof.repository.ServicoRepository
import java.sql.Time

class ServicoViewModel(application: Application): AndroidViewModel(application) {

    private var repository = ServicoRepository(application.applicationContext)
    private var validacao = ValidarClasses()
    private var txtToast = MutableLiveData<String>()
    private var servicoFromDB = MutableLiveData<Servico>()
    private var listViewModel = MutableLiveData<List<Servico>>()

    fun salvar(nomeServico: String, preco: Float, duracao: Int): Boolean{

        if(validacao.camposEmBrancoServico(nomeServico)) {
            txtToast.value = "Preencha pelomenos o Nome do Servico"
            return false
        }

        var servico = Servico(0, nomeServico, preco, duracao)

        if (!repository.salvar(servico)) {
            txtToast.value = "Erro ao salvar..."
            return false
        }

        txtToast.value = "Serviço salvo!"
        return true
    }

    fun deletar(servico: Servico) {
        repository.deletar(servico)
        txtToast.value = "Serviço excluído!"
    }

    fun deletar(id: Int) {
        repository.deletar(repository.getServico(id))
        txtToast.value = "Serviço excluído!"
    }

    fun getListViewModel(): LiveData<List<Servico>> {
        return listViewModel
    }

    fun getListFromDB() {
        listViewModel.value = repository.getAll()
    }

    fun getServicoFromDB(): LiveData<Servico> {
        return servicoFromDB
    }

    fun getTxtToast(): LiveData<String> {
        return txtToast
    }

    fun findServico(id: Int) {
        servicoFromDB.value = repository.getServico(id)
    }

    fun validarAntesDeAtualizar(nomeServico: String): Boolean {
        if (validacao.camposEmBrancoServico(nomeServico)) {
            txtToast.value = "Preencha pelo menos o Nome do Serviço!"
            return false
        }
        return true
    }

    fun atualizar(servico: Servico) {
        repository.atualizar(servico)
        txtToast.value = "Serviço Atualizado!"
    }

}