package com.example.esteticahsof.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.esteticahsof.R
import com.example.esteticahsof.databinding.ActivityLoginBinding
import com.example.esteticahsof.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loginViewModel = LoginViewModel(this)

        binding.btnEntrar.setOnClickListener {
            validarCampos()
        }
    }

    private fun validarCampos() {

        if (binding.edtEmail.text.isEmpty() ||
            binding.edtSenha.text.isEmpty()) {

            exibirToast("Preencha todos os campos!")
            return
        }

        var email = binding.edtEmail.text.toString()
        var senha = binding.edtSenha.text.toString()

        var sharedEmail = loginViewModel.getEmail()
        var sharedSenha = loginViewModel.getSenha()

        var valoresDiferentes = email != sharedEmail || senha != sharedSenha

        if (valoresDiferentes) {

            exibirToast("E-mail ou Senha inválidos!")
            binding.edtEmail.text.clear()
            binding.edtSenha.text.clear()
            return
        }

        var intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun exibirToast(mensagem : String){
        Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show()
    }

}