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
import com.example.esteticahsof.R
import com.example.esteticahsof.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


//Trabalho final de Desenvolvimento Android
//By Kaique Fernandes


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
//            R.id.cadastroServicosProdutos -> startActivity(Intent(this, CadastroServicosProdutosActivity::class.java))
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



}