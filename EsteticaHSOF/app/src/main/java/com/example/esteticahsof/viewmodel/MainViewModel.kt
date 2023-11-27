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
    private var selectedDate = MutableLiveData<String>()

    fun setSelectedDate(date: String) {
        selectedDate.value = date
    }

    fun getSelectedDate(): LiveData<String> {
        return selectedDate
    }

    fun ordenarPorHora() {
        listViewModel.value = listViewModel.value?.sortedBy { it.hora }
    }

    fun getListViewModel() : LiveData<List<Agendamento>> {
        return listViewModel
    }

    fun getTxtToast(): LiveData<String> {
        return txtToast
    }

    fun getListFromDB() {
        val date = selectedDate.value
        if (!date.isNullOrEmpty()) {
            listViewModel.value = repository.getAllByDate(date)
        } else {
            listViewModel.value = repository.getAll()
        }
        ordenarPorHora()
    }

    fun deletar(agendamento: Agendamento) {
        repository.deletar(agendamento)
        txtToast.value = "Agendamento Excluído"
    }

}