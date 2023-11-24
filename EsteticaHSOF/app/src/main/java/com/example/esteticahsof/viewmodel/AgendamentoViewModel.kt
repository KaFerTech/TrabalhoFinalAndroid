package com.example.esteticahsof.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.esteticahsof.model.Agendamento
import com.example.esteticahsof.model.ValidarClasses
import com.example.esteticahsof.repository.AgendamentoRepository
import java.util.Date

class AgendamentoViewModel(application: Application) : AndroidViewModel(application) {

    private var repository = AgendamentoRepository(application.applicationContext)
    private var validacao = ValidarClasses()
    private var txtToast = MutableLiveData<String>()
    private var agendamentoFromDB = MutableLiveData<Agendamento>()
    private var listViewModel = MutableLiveData<List<Agendamento>>()

    fun salvar(idCliente: Int, idServico: Int, dataHora: Date, observacao: String) : Boolean {
        if(validacao.camposEmBrancoAgendamento(idCliente, idServico, dataHora)) {
            txtToast.value = "Preencher Cliente, Serviço e Data!"
            return false
        }

        var agendamento = Agendamento(0, idCliente, idServico, dataHora, observacao)

        if (!repository.salvar(agendamento)) {
            txtToast.value = "Erro ao salvar..."
            return false
        }

        txtToast.value = "Agendamento Salvo!"
        return true
    }

    fun deletar(agendamento: Agendamento) {
        repository.deletar(agendamento)
        txtToast.value = "Agendamento Excluído!"
    }

    fun deletar(id: Int) {
        repository.deletar(repository.getAgendamento(id))
    }

    fun getListViewModel(): LiveData<List<Agendamento>> {
        return listViewModel
    }

    fun getListFromDB() {
        listViewModel.value = repository.getAll()
    }

    fun getAgendamentoFromDB(): LiveData<Agendamento> {
        return agendamentoFromDB
    }

    fun getTxtToast(): LiveData<String> {
        return txtToast
    }

    fun findAgendamento(id: Int) {
        agendamentoFromDB.value = repository.getAgendamento(id)
    }

    fun validarAntesDeAtualizar(idCliente: Int, idServico: Int, dataHora: Date): Boolean {
        if(validacao.camposEmBrancoAgendamento(idCliente, idServico, dataHora)) {
            txtToast.value = "Selecionar Cliente, Serviço, Data e Hora!"
            return false
        }
        return true
    }

    fun atualizar(agendamento: Agendamento) {
        repository.atualizar(agendamento)
        txtToast.value = "Agendamento Atualizado!"
    }

}