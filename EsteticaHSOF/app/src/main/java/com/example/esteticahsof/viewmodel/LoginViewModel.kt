package com.example.esteticahsof.viewmodel

import android.content.Context

class LoginViewModel (context : Context) {

    private var sharedPreferences = context.getSharedPreferences("Login", Context.MODE_PRIVATE)
    private val EMAIL  = "admin"
    private val SENHA  = "admin"

    fun getEmail() : String {
        return sharedPreferences.getString("email", EMAIL) ?: ""
    }

    fun getSenha() : String {
        return sharedPreferences.getString("senha", SENHA) ?: ""
    }

}