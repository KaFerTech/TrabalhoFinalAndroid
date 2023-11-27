package com.example.esteticahsof.view

import android.app.AlertDialog
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.example.esteticahsof.R
import com.example.esteticahsof.databinding.ActivityServicoBinding
import com.example.esteticahsof.model.Servico
import com.example.esteticahsof.viewmodel.ProdutoViewModel
import com.example.esteticahsof.viewmodel.ServicoViewModel

class ServicoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityServicoBinding
    private lateinit var viewModel: ServicoViewModel
    private lateinit var viewModelProduto: ProdutoViewModel
    private lateinit var servico: Servico
    var id = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityServicoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(ServicoViewModel::class.java)
        viewModelProduto = ViewModelProvider(this).get(ProdutoViewModel::class.java)
        id = intent.getIntExtra("id", 0)
        supportActionBar?.apply { setDisplayHomeAsUpEnabled(true) }

        if (id > 0) {
            supportActionBar?.apply { title = "Editar Serviço" }
            viewModel.findServico(id)
            binding.btnMenos.isEnabled = false
            binding.btnMais.isEnabled = false
            binding.btnMenos.isVisible = false
            binding.btnMais.isVisible = false
        } else {
            supportActionBar?.apply { title = "Criar Serviço" }
            binding.edtNomeServico.isEnabled = true
            binding.edtPreco.isEnabled = true
            binding.edtDuracao.isEnabled = true
            binding.btnMenos.isEnabled = true
            binding.btnMais.isEnabled = true
            binding.btnMenos.isVisible = true
            binding.btnMais.isVisible = true
        }


        binding.btnMais.setOnClickListener{
            val valorAtual = binding.edtDuracao.text.toString().toIntOrNull() ?: 0
            val novoValor = valorAtual + 15
            binding.edtDuracao.setText(novoValor.toString())
        }

        binding.btnMenos.setOnClickListener{
            val valorAtual = binding.edtDuracao.text.toString().toIntOrNull() ?: 0
            val novoValor = 0
            if (valorAtual < 15) {
                binding.edtDuracao.setText(novoValor.toString())
            } else {
                val novoValor = valorAtual - 15
                binding.edtDuracao.setText(novoValor.toString())
            }
        }


        binding.btnAdicionarProduto.setOnClickListener {
            exibirListaProdutos()
        }

        setObservers()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.servico_menu, menu)
        val salvarEditarServico = menu?.findItem(R.id.menuEditarServico)
        if (id > 0) {
            salvarEditarServico?.title = "Editar"
            salvarEditarServico?.setIcon(android.R.drawable.ic_menu_edit)
        } else {
            salvarEditarServico?.title = "Salvar"
            salvarEditarServico?.setIcon(android.R.drawable.ic_menu_save)
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
            R.id.deletarServico -> {
                if (id > 0) {
                    viewModel.deletar(id)
                    viewModel.getListFromDB()
                    finish()
                } else {
                    binding.edtNomeServico.setText("")
                    binding.edtPreco.setText("")
                    binding.edtDuracao.setText("")
                }
                return true
            }
            R.id.menuEditarServico -> {
                if(item.title == "Editar") {
                    binding.edtNomeServico.isEnabled = true
                    binding.edtPreco.isEnabled = true
                    binding.edtDuracao.isEnabled = true
                    binding.btnMenos.isEnabled = true
                    binding.btnMais.isEnabled = true
                    binding.btnMenos.isVisible = true
                    binding.btnMais.isVisible = true
                    item.title = "Salvar"
                    item.setIcon(android.R.drawable.ic_menu_save)
                } else {
                    val nomeServico = binding.edtNomeServico.text.toString()
                    if (binding.edtPreco.text.toString() == "") { binding.edtPreco.setText("0") }
                    if (binding.edtDuracao.text.toString() == "") { binding.edtDuracao.setText("0") }
                    val preco = binding.edtPreco.text.toString().replace(",", ".").toFloat()
                    val duracao = binding.edtDuracao.text.toString().toInt()


                    if (id > 0) {
                        if (viewModel.validarAntesDeAtualizar(nomeServico)) {
                            servico.nomeServico = nomeServico
                            servico.preco = preco
                            servico.duracao = duracao

                            viewModel.atualizar(servico)

                            finish()
                        }
                    } else {
                        if (viewModel.salvar(nomeServico, preco, duracao)) {
                            finish()
                        }
                    }

                }
                return true
            }

        }
        return super.onOptionsItemSelected(item)
    }

    private fun exibirListaProdutos() {
        viewModelProduto.getListFromDB()
        viewModelProduto.getListViewModel().observe(this) { produtos ->

            val nomeProdutos = produtos.map { it.nomeProduto }.toTypedArray()

            val builder = AlertDialog.Builder(this)
            builder.setTitle("Selecione um Produto")

            builder.setItems(nomeProdutos) { dialog, which ->
                val produtoSelecionado = produtos[which]
                val valorProdutoSelecionado = produtoSelecionado.preco + binding.edtPreco.text.toString().toFloat()
                binding.edtPreco.setText(valorProdutoSelecionado.toString())

                dialog.dismiss()
            }

            val dialog = builder.create()
            dialog.show()
        }
    }


    private fun setObservers(){

        if (id > 0) {
            viewModel.getServicoFromDB().observe(this) {
                servico = it
                binding.edtNomeServico.setText(servico.nomeServico)
                binding.edtPreco.setText(servico.preco.toString())
                binding.edtDuracao.setText(servico.duracao.toString())
            }

        }

        viewModel.getTxtToast().observe(this){
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
    }
}