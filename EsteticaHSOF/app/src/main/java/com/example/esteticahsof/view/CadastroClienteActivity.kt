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
import com.example.esteticahsof.utils.FormataTelefone
import com.example.esteticahsof.viewmodel.CadastroClienteViewModel

class CadastroClienteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCadastroClienteBinding
    private lateinit var viewModelCadastro: CadastroClienteViewModel
    private lateinit var cliente: Cliente

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroClienteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModelCadastro = ViewModelProvider(this).get(CadastroClienteViewModel::class.java)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = "Criar Cliente"
        }

        val spinnerGenero = binding.spinnerGenero
        val genderOptions = resources.getStringArray(R.array.array_genero)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, genderOptions)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerGenero.adapter = adapter

        // Mascara do CPF
        val cpf = binding.edtCpf
        cpf.addTextChangedListener(FormataCpf(cpf))

        // Mascara da Data de Nascimento
        val dtNascimento = binding.edtDtNascimento
        dtNascimento.addTextChangedListener(FormataData(dtNascimento))

        // Mascara do Telefone
        val tel = binding.edtTelefone
        tel.addTextChangedListener(FormataTelefone(tel))


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
                val genero = binding.spinnerGenero.selectedItemPosition
                val endereco = binding.edtEndereco.text.toString()
                val alergias = binding.edtAlergias.text.toString()
                val observacoes = binding.edtObservacoes.text.toString()

                if (viewModelCadastro.salvar(
                        nomeCliente, telefone, email, cpf,
                        dtNascimento, genero, endereco, alergias, observacoes
                    )
                ) {
                    finish()
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

    fun setObservers() {
        viewModelCadastro.getTxtToast().observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
    }
}