package com.example.esteticahsof.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.esteticahsof.databinding.ActivityClientesBinding
import com.example.esteticahsof.view.adapter.ClienteAdapter
import com.example.esteticahsof.viewmodel.ClienteViewModel
import android.widget.SearchView.OnQueryTextListener

class ClientesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityClientesBinding
    private lateinit var adapter: ClienteAdapter
    private lateinit var viewModel: ClienteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClientesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = "Clientes"
        }

        adapter = ClienteAdapter(this)
        viewModel = ViewModelProvider(this).get(ClienteViewModel::class.java)

        setAdapter()
        setObservers()

        binding.btnNovoCliente.setOnClickListener{
            startActivity(Intent(this, CadastroClienteActivity::class.java))
        }

        binding.searchViewClientes.setOnQueryTextListener(object : OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter(newText.orEmpty())
                return true
            }
        })


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

        binding.rcvClientes.layoutManager = LinearLayoutManager(this)
        binding.rcvClientes.adapter = adapter

        adapter.onItemClick = {
            val h = adapter.listaAdapter[it]
            val intent = Intent(this, ClienteActivity::class.java)
            intent.putExtra("id", h.id)
            startActivity(intent)
        }

    }

    override fun onResume() {
        super.onResume()
        viewModel.getListFromDB()
    }

}
