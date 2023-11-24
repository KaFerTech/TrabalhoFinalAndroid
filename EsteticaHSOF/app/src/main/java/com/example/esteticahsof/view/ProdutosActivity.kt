package com.example.esteticahsof.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.esteticahsof.R
import com.example.esteticahsof.databinding.ActivityProdutoBinding
import com.example.esteticahsof.databinding.ActivityProdutosBinding
import com.example.esteticahsof.view.adapter.ProdutoAdapter
import com.example.esteticahsof.viewmodel.ProdutoViewModel

class ProdutosActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProdutosBinding
    private lateinit var adapter: ProdutoAdapter
    private lateinit var viewModel: ProdutoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProdutosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = "Produtos"
        }

        adapter = ProdutoAdapter(this)
        viewModel = ViewModelProvider(this).get(ProdutoViewModel::class.java)

        setAdapter()
        setObservers()

        binding.btnNovoProduto.setOnClickListener {
            startActivity(Intent(this, ProdutoActivity::class.java))
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                // Ação ao clicar no botão de voltar, mesmo ficando tachado, só funciona desse jeito
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun setObservers(){
        viewModel.getListViewModel().observe(this){
            adapter.updateAdapter(it)
        }
        viewModel.getTxtToast().observe(this){
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
    }

    fun setAdapter() {

        binding.rcvProdutos.layoutManager = LinearLayoutManager(this)
        binding.rcvProdutos.adapter = adapter

        adapter.onItemClick = {
            val h = adapter.listaAdapter[it]
            val intent = Intent(this, ProdutoActivity::class.java)
            intent.putExtra("id", h.id)
            startActivity(intent)
        }

    }

    override fun onResume() {
        super.onResume()
        viewModel.getListFromDB()
    }

}