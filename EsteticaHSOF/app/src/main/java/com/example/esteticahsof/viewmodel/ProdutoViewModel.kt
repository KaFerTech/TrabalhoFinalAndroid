package com.example.esteticahsof.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.esteticahsof.model.Produto
import com.example.esteticahsof.model.ValidarClasses
import com.example.esteticahsof.repository.ProdutoRepository

class ProdutoViewModel(application: Application) : AndroidViewModel(application) {

    private var repository = ProdutoRepository(application.applicationContext)
    private var validacao = ValidarClasses()
    private var txtToast = MutableLiveData<String>()
    private var produtoFromDB = MutableLiveData<Produto>()
    private var listViewModel = MutableLiveData<List<Produto>>()

    fun salvar(nomeProduto: String, preco: Float, qtdEstoque: Float): Boolean {
        if (validacao.camposEmBrancoProduto(nomeProduto)) {
            txtToast.value = "Preencha pelomenos o Nome do Produto!"
            return false
        }

        var produto = Produto(0, nomeProduto, preco, qtdEstoque)

        if (!repository.salvar(produto)) {
            txtToast.value = "Erro ao salvar..."
            return false
        }

        txtToast.value = "Produto Salvo!"
        return true
    }

    fun deletar(produto: Produto){
        repository.deletar(produto)
        txtToast.value = "Produto excluído!"
    }

    fun deletar(id: Int){
        repository.deletar(repository.getProduto(id))
        txtToast.value = "Produto excluído!"
    }

    fun getListViewModel(): LiveData<List<Produto>> {
        return listViewModel
    }

    fun getListFromDB() {
        listViewModel.value = repository.getAll()
    }

    fun getProdutoFromDB(): LiveData<Produto> {
        return produtoFromDB
    }

    fun getTxtToast(): LiveData<String> {
        return txtToast
    }

    fun findProduto(id: Int) {
        produtoFromDB.value = repository.getProduto(id)
    }

    fun validarAntesDeAtualizar(nomeProduto: String): Boolean {
        if (validacao.camposEmBrancoProduto(nomeProduto)) {
            txtToast.value = "Preencha pelomenos o Nome do Produto!"
            return false
        }
        return true
    }

    fun atualizar(produto: Produto) {
        repository.atualizar(produto)
        txtToast.value = "Produto Atualizado!"
    }

}