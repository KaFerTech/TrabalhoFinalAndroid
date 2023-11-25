package com.example.esteticahsof.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.esteticahsof.model.Agendamento

@Database(entities = [Agendamento::class], version = 1)
abstract class AgendamentoDataBase: RoomDatabase() {

    abstract fun getAgendamentoDAO(): AgendamentoDAO

    companion object {

        private lateinit var INSTANCE: AgendamentoDataBase

        fun getInstance(context: Context): AgendamentoDataBase {

            if(!::INSTANCE.isInitialized) {
                INSTANCE = Room.databaseBuilder(context, AgendamentoDataBase::class.java, "agendamentos_db")
                    .allowMainThreadQueries()
                    .build()
            }
            return INSTANCE
        }

    }

}