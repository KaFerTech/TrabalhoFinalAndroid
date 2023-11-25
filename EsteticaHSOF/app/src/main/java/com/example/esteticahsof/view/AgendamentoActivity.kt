package com.example.esteticahsof.view

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.esteticahsof.R
import com.example.esteticahsof.databinding.ActivityAgendamentoBinding
import com.example.esteticahsof.model.Agendamento
import com.example.esteticahsof.viewmodel.AgendamentoViewModel
import com.example.esteticahsof.viewmodel.ClienteViewModel
import com.example.esteticahsof.viewmodel.ServicoViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class AgendamentoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAgendamentoBinding
    private lateinit var viewModel: AgendamentoViewModel
    private lateinit var viewModelCliente: ClienteViewModel
    private lateinit var viewModelServico: ServicoViewModel
    private lateinit var agendamento: Agendamento
    var id = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAgendamentoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(AgendamentoViewModel::class.java)
        viewModelCliente = ViewModelProvider(this).get(ClienteViewModel::class.java)
        viewModelServico = ViewModelProvider(this).get(ServicoViewModel::class.java)

        id = intent.getIntExtra("id", 0)
        supportActionBar?.apply { setDisplayHomeAsUpEnabled(true) }

        if (id > 0) {
            supportActionBar?.apply { title = "Agendamento" }
            viewModel.findAgendamento(id)
        } else {
            supportActionBar?.apply { title = "Criar Agendamento" }
            binding.edtClienteAgendamento.isEnabled = true
            binding.edtServicoAgendamento.isEnabled = true
            binding.dataAgendamento.isEnabled = true
            binding.horaAgendamento.isEnabled = true
            binding.observacoesAgendamento.isEnabled = true
        }

        val edtData: EditText = binding.dataAgendamento
        preencherDataAtual(edtData)

        binding.dataAgendamento.setOnClickListener {
            exibirCalendario()
        }

        binding.horaAgendamento.setOnClickListener {
//            exibirRelogio()
            exibirListaHorarios()
        }

        binding.edtClienteAgendamento.setOnClickListener {
            exibirListaClientes()
        }

        binding.edtServicoAgendamento.setOnClickListener {
            exibirListaServicos()
        }

        setObservers()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.agendamento_menu, menu)
        val salvarEditarAgendamento = menu?.findItem(R.id.menuEditarAgendamento)
        if (id > 0) {
            salvarEditarAgendamento?.title = "Editar"
            salvarEditarAgendamento?.setIcon(android.R.drawable.ic_menu_edit)
        } else {
            salvarEditarAgendamento?.title = "Salvar"
            salvarEditarAgendamento?.setIcon(android.R.drawable.ic_menu_save)
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
            R.id.deletarAgendamento -> {
                if (id > 0) {
                    viewModel.deletar(id)
                    viewModel.getListFromDB()
                    finish()
                } else {
                    finish()
                }
                return true
            }
            R.id.menuEditarAgendamento -> {
                if(item.title == "Editar") {
                    binding.edtClienteAgendamento.isEnabled = true
                    binding.edtServicoAgendamento.isEnabled = true
                    binding.dataAgendamento.isEnabled = true
                    binding.observacoesAgendamento.isEnabled = true
                    item.title = "Salvar"
                    item.setIcon(android.R.drawable.ic_menu_save)
                } else {
                    val clienteAgendamento = binding.edtClienteAgendamento.text.toString()
                    val servicoAgendamento = binding.edtServicoAgendamento.text.toString()
                    val dataAgendamento = binding.dataAgendamento.text.toString()
                    val horaAgendamento = binding.horaAgendamento.text.toString()
                    val precoAgendamento = binding.edtPrecoAgendamento.text.toString()
                    val observacaoAgendamento = binding.observacoesAgendamento.text.toString()

                        if (id > 0) {
                            if (viewModel.validarAntesDeAtualizar( clienteAgendamento, servicoAgendamento, dataAgendamento, horaAgendamento)) {
                                agendamento.cliente = clienteAgendamento
                                agendamento.servico = servicoAgendamento
                                agendamento.data = dataAgendamento
                                agendamento.hora = horaAgendamento
                                agendamento.preco = precoAgendamento.toFloat()
                                agendamento.observacao = observacaoAgendamento

                                viewModel.atualizar(agendamento)

                                finish()
                            }
                        } else {
                            if (viewModel.salvar(clienteAgendamento, servicoAgendamento, dataAgendamento, horaAgendamento, precoAgendamento.toFloat(), observacaoAgendamento)) {
                                finish()
                            }
                        }


                }
                return true
            }

        }
        return super.onOptionsItemSelected(item)
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
                binding.dataAgendamento.setText(dataSelecionada)
            },
            ano, mes, dia
        )

        datePicker.show()
    }

    private fun exibirRelogio() {
        val calendario = Calendar.getInstance()
        val hora = calendario.get(Calendar.HOUR_OF_DAY)
        val minuto = calendario.get(Calendar.MINUTE)

        val timePicker = TimePickerDialog(
            this,
            { _, selectedHour, selectedMinute ->
                // Lógica para tratar a hora selecionada
                val horaSelecionada = "$selectedHour:$selectedMinute"
                // Adicione a lógica para fazer algo com a hora selecionada
                binding.horaAgendamento.setText(horaSelecionada)
            },
            hora, minuto, true
        )

        timePicker.show()
    }

    private fun preencherDataAtual(editText: EditText) {
        val formatoData = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val dataAtual = formatoData.format(Calendar.getInstance().time)
        editText.setText(dataAtual)
    }


    private fun exibirListaClientes() {
        viewModelCliente.getListFromDB()
        viewModelCliente.getListViewModel().observe(this) { clientes ->
            // Mapeie a lista de clientes para obter apenas os nomes
            val nomesClientes = clientes.map { it.nomeCliente }

            val builder = AlertDialog.Builder(this)
            builder.setTitle("Selecione um cliente")

            builder.setItems(nomesClientes.toTypedArray()) { dialog, which ->
                val clienteSelecionado = clientes[which]
                binding.edtClienteAgendamento.setText(clienteSelecionado.nomeCliente)
                dialog.dismiss()
            }

            val dialog = builder.create()
            dialog.show()
        }
    }

    private fun exibirListaServicos() {
        viewModelServico.getListFromDB()
        viewModelServico.getListViewModel().observe(this) { servicos ->

            val nomeServicos = servicos.map { it.nomeServico }.toTypedArray()

            val builder = AlertDialog.Builder(this)
            builder.setTitle("Selecione um Serviço")

            builder.setItems(nomeServicos) { dialog, which ->
                val servicoSelecionado = servicos[which]
                binding.edtServicoAgendamento.setText(servicoSelecionado.nomeServico)
                binding.edtPrecoAgendamento.setText(servicoSelecionado.preco.toString())
                dialog.dismiss()
            }

            val dialog = builder.create()
            dialog.show()
        }
    }


    private fun exibirListaHorarios() {
        val horarios = resources.getStringArray(R.array.array_hora)

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Selecione um Horário")

        builder.setItems(horarios) { dialog, which ->
            val horarioSelecionado = horarios[which]
            binding.horaAgendamento.setText(horarioSelecionado)
            dialog.dismiss()
        }

        val dialog = builder.create()
        dialog.show()
    }


    private fun setObservers(){
        if (id > 0) {
            viewModel.getAgendamentoFromDB().observe(this) {
                agendamento = it
                binding.edtClienteAgendamento.setText(agendamento.cliente)
                binding.edtServicoAgendamento.setText(agendamento.servico)
                binding.dataAgendamento.setText(agendamento.data)
                binding.horaAgendamento.setText(agendamento.hora)
                binding.edtPrecoAgendamento.setText(agendamento.preco.toString())
                binding.observacoesAgendamento.setText(agendamento.observacao)
            }
        }

        viewModel.getTxtToast().observe(this){
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
    }
}