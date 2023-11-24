package com.example.esteticahsof.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.esteticahsof.model.Servico

@Database(entities = [Servico::class], version = 1)
abstract class ServicoDataBase: RoomDatabase() {

    abstract fun getDAO(): ServicoDAO

    companion object {

        private lateinit var INSTANCE: ServicoDataBase

        fun getInstance(context: Context): ServicoDataBase {

            if(!::INSTANCE.isInitialized) {
                INSTANCE = Room.databaseBuilder(context, ServicoDataBase::class.java, "servicos_db")
                    .allowMainThreadQueries()
                    .build()
            }
            return INSTANCE
        }

    }

}