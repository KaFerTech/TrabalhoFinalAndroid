package com.example.esteticahsof.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.esteticahsof.R
import com.example.esteticahsof.databinding.ActivityServicosBinding
import com.example.esteticahsof.view.adapter.ServicoAdapter
import com.example.esteticahsof.viewmodel.ServicoViewModel

class ServicosActivity : AppCompatActivity() {

    private lateinit var binding: ActivityServicosBinding
    private lateinit var adapter: ServicoAdapter
    private lateinit var viewModel: ServicoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityServicosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = "Serviços"
        }

        adapter = ServicoAdapter(this)
        viewModel = ViewModelProvider(this).get(ServicoViewModel::class.java)

        setAdapter()
        setObservers()

        binding.btnNovoServico.setOnClickListener {
            startActivity(Intent(this, ServicoActivity::class.java))
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

        binding.rcvServicos.layoutManager = LinearLayoutManager(this)
        binding.rcvServicos.adapter = adapter

        adapter.onItemClick = {
            val h = adapter.listaAdapter[it]
            val intent = Intent(this, ServicoActivity::class.java)
            intent.putExtra("id", h.id)
            startActivity(intent)
        }

    }

    override fun onResume() {
        super.onResume()
        viewModel.getListFromDB()
    }

}