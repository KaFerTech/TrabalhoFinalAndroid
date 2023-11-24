package com.example.esteticahsof.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.esteticahsof.R
import com.example.esteticahsof.databinding.ActivityAgendamentoBinding

class AgendamentoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAgendamentoBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAgendamentoBinding.inflate(layoutInflater)
        setContentView(binding.root)





    }
}