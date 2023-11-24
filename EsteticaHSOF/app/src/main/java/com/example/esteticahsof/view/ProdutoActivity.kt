package com.example.esteticahsof.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.esteticahsof.R
import com.example.esteticahsof.databinding.ActivityProdutoBinding
import com.example.esteticahsof.model.Produto
import com.example.esteticahsof.view.adapter.ProdutoAdapter
import com.example.esteticahsof.viewmodel.ProdutoViewModel
import java.math.BigDecimal
import java.util.Currency

class ProdutoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProdutoBinding
    private lateinit var viewModel: ProdutoViewModel
    private lateinit var produto: Produto
    var id = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProdutoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(ProdutoViewModel::class.java)
        id = intent.getIntExtra("id", 0)
        supportActionBar?.apply { setDisplayHomeAsUpEnabled(true) }

        if (id > 0) {
            supportActionBar?.apply { title = "Editar Produto" }
            viewModel.findProduto(id)
            binding.btnMenos.isEnabled = false
            binding.btnMais.isEnabled = false
        } else {
            supportActionBar?.apply { title = "Criar Produto" }
            binding.edtNomeProduto.isEnabled = true
            binding.edtPreco.isEnabled = true
            binding.edtQtdEstoque.isEnabled = true
            binding.btnMenos.isEnabled = true
            binding.btnMais.isEnabled = true
        }

        binding.btnMais.setOnClickListener{
            if (binding.edtQtdEstoque.text.toString() == "") { binding.edtQtdEstoque.setText("0") }
            val valorAtual = binding.edtQtdEstoque.text.toString().toFloat()
            val novoValor = valorAtual + 1
            binding.edtQtdEstoque.setText(novoValor.toString())
        }

        binding.btnMenos.setOnClickListener{
            if (binding.edtQtdEstoque.text.toString() == "") { binding.edtQtdEstoque.setText("0") }
            val valorAtual = binding.edtQtdEstoque.text.toString().toFloat()
            val novoValor = 0
            if (valorAtual < 1) {
                binding.edtQtdEstoque.setText(novoValor.toString())
            } else {
                val novoValor = valorAtual - 1
                binding.edtQtdEstoque.setText(novoValor.toString())
            }
        }

        setObservers()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.produto_menu, menu)
        val salvarEditarProduto = menu?.findItem(R.id.menuEditarProduto)
        if (id > 0) {
            salvarEditarProduto?.title = "Editar"
            salvarEditarProduto?.setIcon(android.R.drawable.ic_menu_edit)
        } else {
            salvarEditarProduto?.title = "Salvar"
            salvarEditarProduto?.setIcon(android.R.drawable.ic_menu_save)
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                // Ação ao clicar no botão de voltar, mesmo ficando tachado, só funciona desse jeito
                onBackPressed()
                return true
            }
            R.id.deletarProduto -> {
                if (id > 0) {
                    viewModel.deletar(id)
                    viewModel.getListFromDB()
                    finish()
                } else {
                    binding.edtNomeProduto.setText("")
                    binding.edtPreco.setText("")
                    binding.edtQtdEstoque.setText("")
                }
                return true
            }
            R.id.menuEditarProduto -> {
                if(item.title == "Editar") {
                    binding.edtNomeProduto.isEnabled = true
                    binding.edtPreco.isEnabled = true
                    binding.edtQtdEstoque.isEnabled = true
                    binding.btnMenos.isEnabled = true
                    binding.btnMais.isEnabled = true
                    item.title = "Salvar"
                    item.setIcon(android.R.drawable.ic_menu_save)
                } else {
                    val nomeProduto = binding.edtNomeProduto.text.toString()
                    if (binding.edtPreco.text.toString() == "") { binding.edtPreco.setText("0") }
                    if (binding.edtQtdEstoque.text.toString() == "") { binding.edtQtdEstoque.setText("0") }
                    val preco = binding.edtPreco.text.toString().replace(",", ".").toFloat()
                    val qtdEstoque = binding.edtQtdEstoque.text.toString().replace(",", ".").toFloat()

                    if (id > 0) {
                        if (viewModel.validarAntesDeAtualizar(nomeProduto)) {
                            produto.nomeProduto = nomeProduto
                            produto.preco = preco
                            produto.qtdEstoque = qtdEstoque

                            viewModel.atualizar(produto)

                            finish()
                        }
                    } else {
                        if (viewModel.salvar(nomeProduto, preco, qtdEstoque)) {
                            finish()
                        }
                    }

                }
                return true
            }

        }
        return super.onOptionsItemSelected(item)
    }



    fun setObservers(){

        if (id > 0) {
            viewModel.getProdutoFromDB().observe(this) {
                produto = it
                binding.edtNomeProduto.setText(produto.nomeProduto)
                binding.edtPreco.setText(produto.preco.toString())
                binding.edtQtdEstoque.setText(produto.qtdEstoque.toString())
            }
        }

        viewModel.getTxtToast().observe(this){
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
    }

}