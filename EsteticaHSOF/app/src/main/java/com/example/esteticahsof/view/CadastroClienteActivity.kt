package com.example.esteticahsof.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.esteticahsof.R
import com.example.esteticahsof.databinding.ActivityCadastroClienteBinding
import com.example.esteticahsof.model.Cliente
import com.example.esteticahsof.utils.FormataCpf
import com.example.esteticahsof.utils.FormataData
import com.example.esteticahsof.viewmodel.CadastroClienteViewModel
import com.example.esteticahsof.viewmodel.CadastroClienteViewModelOld
import com.example.esteticahsof.viewmodel.ClienteViewModel

class CadastroClienteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCadastroClienteBinding
    var id = 0
    private lateinit var viewModelCadastro: CadastroClienteViewModel
    private lateinit var viewModel: ClienteViewModel
    private lateinit var cliente : Cliente

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroClienteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModelCadastro = ViewModelProvider(this).get(CadastroClienteViewModel::class.java)
        id = intent.getIntExtra("id", 0)
        supportActionBar?.apply { setDisplayHomeAsUpEnabled(true) }

        if (id > 0) {
            supportActionBar?.apply {title = "Editar Cliente"}
            viewModel.findCliente(id)
        } else {
            supportActionBar?.apply {title = "Criar Cliente"}
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

        // Mascara do CPF
        val cpf = binding.edtCpf
        cpf.addTextChangedListener(FormataCpf(cpf))

        // Mascara da Data de Nascimento
        val dtNascimento = binding.edtDtNascimento
        dtNascimento.addTextChangedListener(FormataData(dtNascimento))

        setObservers()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.cadastro_cliente_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menuSalvar -> {

                val nomeCliente = binding.edtNomeCliente.text.toString()
                val telefone = binding.edtTelefone.text.toString()
                val email = binding.edtEmail.text.toString()
                val cpf = binding.edtCpf.text.toString()
                val dtNascimento = binding.edtDtNascimento.text.toString()
                val genero = binding.spinnerGenero.selectedItemPosition.toString()
                val endereco = binding.edtEndereco.text.toString()
                val alergias = binding.edtAlergias.text.toString()
                val observacoes = binding.edtObservacoes.text.toString()

                if (id > 0) {
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
                        finish()
                    }
                } else {
                    if (viewModelCadastro.salvar(nomeCliente, telefone, email, cpf,
                            dtNascimento, genero.toInt(), endereco, alergias, observacoes)) {
                        finish()
                    }
                }
                return true
            }
            android.R.id.home -> {
                // Ação ao clicar no botão de voltar, mesmo ficando tachado, só funciona desse jeito
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun setObservers(){
        if (id > 0) {
            viewModel.getClienteFromDB().observe(this) {
                cliente = it
                binding.edtNomeCliente.setText(cliente.nomeCliente)
                binding.edtTelefone.setText(cliente.telefone)
                binding.edtEmail.setText(cliente.email)
                binding.edtCpf.setText(cliente.cpf)
                binding.edtDtNascimento.setText(cliente.dataNascimento)
                binding.spinnerGenero.setSelection(cliente.genero.toInt())
                binding.edtEndereco.setText(cliente.endereco)
                binding.edtAlergias.setText(cliente.alergias)
                binding.edtObservacoes.setText(cliente.observacoes)
            }
        }
        viewModelCadastro.getTxtToast().observe(this){
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
    }

}