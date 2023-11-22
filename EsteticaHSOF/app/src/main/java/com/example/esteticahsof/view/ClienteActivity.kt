package com.example.esteticahsof.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.esteticahsof.R
import com.example.esteticahsof.databinding.ActivityClienteBinding
import com.example.esteticahsof.model.Cliente
import com.example.esteticahsof.viewmodel.ClienteViewModel

class ClienteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityClienteBinding
    private lateinit var viewModel: ClienteViewModel
    private lateinit var cliente: Cliente
    var id = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClienteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(ClienteViewModel::class.java)
        id = intent.getIntExtra("id", 0)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = ""
        }

        if(id == 0) {
            finish()
        } else {
            viewModel.findCliente(id)
        }

        val spinnerGenero = binding.spinnerGenero

        // Carregar as opções de gênero do arquivo de recursos
        val genderOptions = resources.getStringArray(R.array.gender_options)

        // Criar um ArrayAdapter usando as opções de gênero
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, genderOptions)

        // Definir o estilo do dropdown
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // Definir o adaptador no Spinner
        spinnerGenero.adapter = adapter

        spinnerGenero.isEnabled = false
        spinnerGenero.isClickable = false

        setObservers()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.cliente_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                // Ação ao clicar no botão de voltar, mesmo ficando tachado, só funciona desse jeito
                onBackPressed()
                return true
            }
            R.id.menuEditarCliente -> {

                if(item.title == "Editar") {
                    supportActionBar?.apply { title = "Edição" }
                    binding.spinnerGenero.isEnabled = true
                    binding.spinnerGenero.isClickable = true
                    binding.edtNomeCliente.isEnabled = true
                    binding.edtTelefone.isEnabled = true
                    binding.edtEmail.isEnabled = true
                    binding.edtCpf.isEnabled = true
                    binding.edtDtNascimento.isEnabled = true
                    binding.spinnerGenero.isEnabled = true
                    binding.edtEndereco.isEnabled = true
                    binding.edtAlergias.isEnabled = true
                    binding.edtObservacoes.isEnabled = true
                    item.title = "Salvar"
                    item.setIcon(android.R.drawable.ic_menu_save)
                } else {
                    supportActionBar?.apply { title = "" }
                    binding.spinnerGenero.isEnabled = false
                    binding.spinnerGenero.isClickable = false
                    binding.edtNomeCliente.isEnabled = false
                    binding.edtTelefone.isEnabled = false
                    binding.edtEmail.isEnabled = false
                    binding.edtCpf.isEnabled = false
                    binding.edtDtNascimento.isEnabled = false
                    binding.spinnerGenero.isEnabled = false
                    binding.edtEndereco.isEnabled = false
                    binding.edtAlergias.isEnabled = false
                    binding.edtObservacoes.isEnabled = false
                    item.title = "Editar"
                    item.setIcon(android.R.drawable.ic_menu_edit)

                    val nomeCliente = binding.edtNomeCliente.text.toString()
                    val telefone = binding.edtTelefone.text.toString()
                    val email = binding.edtEmail.text.toString()
                    val cpf = binding.edtCpf.text.toString()
                    val dtNascimento = binding.edtDtNascimento.text.toString()
                    val genero = binding.spinnerGenero.selectedItemPosition.toString()
                    val endereco = binding.edtEndereco.text.toString()
                    val alergias = binding.edtAlergias.text.toString()
                    val observacoes = binding.edtObservacoes.text.toString()

                    if (viewModel.validarAntesDeAtualizar(nomeCliente, telefone)) {
                        cliente.nomeCliente = nomeCliente
                        cliente.telefone = telefone
                        cliente.email = email
                        cliente.cpf = cpf
                        cliente.dataNascimento = dtNascimento
                        cliente.genero = genero.toInt()
                        cliente.endereco = endereco
                        cliente.alergias = alergias
                        cliente.observacoes = observacoes
                        viewModel.atualizar(cliente)
                    }
                }

                return true
            }
            R.id.menuFavoritarCliente -> {
                //Comandos para favoritar
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun setObservers() {
        viewModel.getClienteFromDB().observe(this) {
            cliente = it
            binding.edtNomeCliente.setText(cliente.nomeCliente)
            binding.edtTelefone.setText(cliente.telefone)
            binding.edtEmail.setText(cliente.email)
            binding.edtCpf.setText(cliente.cpf)
            binding.edtDtNascimento.setText(cliente.dataNascimento)
            binding.spinnerGenero.setSelection(cliente.genero)
            binding.edtEndereco.setText(cliente.endereco)
            binding.edtAlergias.setText(cliente.alergias)
            binding.edtObservacoes.setText(cliente.observacoes)
        }
        viewModel.getTxtToast().observe(this){
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
    }
}