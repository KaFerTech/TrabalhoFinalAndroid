package com.example.esteticahsof.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.esteticahsof.model.Agendamento
import com.example.esteticahsof.repository.AgendamentoRepository

class MainViewModel(application: Application): AndroidViewModel(application) {

    private var repository = AgendamentoRepository(application.applicationContext)
    private var listViewModel = MutableLiveData<List<Agendamento>>()
    private var txtToast = MutableLiveData<String>()

    fun getListViewModel() : LiveData<List<Agendamento>> {
        return listViewModel
    }

    fun getTxtToast(): LiveData<String> {
        return txtToast
    }

    fun getListFromDB() {
        listViewModel.value = repository.getAll()
    }

    fun deletar(agendamento: Agendamento) {
        repository.deletar(agendamento)
        txtToast.value = "Agendamento Excluído"
    }

}