package com.example.esteticahsof.view

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.esteticahsof.R
import com.example.esteticahsof.databinding.ActivityMainBinding
import com.example.esteticahsof.model.Agendamento
import com.example.esteticahsof.view.adapter.AgendamentoAdapter
import com.example.esteticahsof.viewmodel.MainViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


//Trabalho final de Desenvolvimento Android
//By Kaique Fernandes


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: AgendamentoAdapter
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = AgendamentoAdapter(this)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        setAdapter()
        setObservers()


        val txtTituloMain: TextView = binding.txtTituloMain
        val edtData: EditText = binding.edtData

        preencherDataAtual(edtData)

        // Configurar um ouvinte de clique para o EditText
        edtData.setOnClickListener {
            exibirCalendario()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.cadastroClientes -> startActivity(Intent(this, ClientesActivity::class.java))
            R.id.cadastroProdutos -> startActivity(Intent(this, ProdutosActivity::class.java))
            R.id.cadastroServicos -> startActivity(Intent(this, ServicosActivity::class.java))
            R.id.agendarCliente -> startActivity(Intent(this, AgendamentoActivity::class.java))
            R.id.encerrarAplicacao -> finish()
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    private fun exibirCalendario() {
        val calendario = Calendar.getInstance()
        val ano = calendario.get(Calendar.YEAR)
        val mes = calendario.get(Calendar.MONTH)
        val dia = calendario.get(Calendar.DAY_OF_MONTH)

        val datePicker = DatePickerDialog(
            this,
            { _, year, month, dayOfMonth ->
                // Lógica para tratar a data selecionada
                val dataSelecionada = "$dayOfMonth/${month + 1}/$year"
                binding.edtData.setText(dataSelecionada)
            },
            ano, mes, dia
        )

        datePicker.show()
    }

    private fun preencherDataAtual(editText: EditText) {
        val formatoData = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val dataAtual = formatoData.format(Calendar.getInstance().time)
        editText.setText(dataAtual)
    }


    fun setObservers(){
        viewModel.getListViewModel().observe(this){
            adapter.updateAdapter(it)
        }
        viewModel.getTxtToast().observe(this){
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
    }

    fun setAdapter(){

        binding.rcvAgenda.layoutManager = LinearLayoutManager(this)
        binding.rcvAgenda.adapter = adapter

        adapter.onItemClick = {
            val h = adapter.listaAdapter[it]
            val intent = Intent(this, AgendamentoActivity::class.java)
            intent.putExtra("id", h.id)
            startActivity(intent)
        }

    }

    override fun onResume() {
        super.onResume()
        viewModel.getListFromDB()
    }
}