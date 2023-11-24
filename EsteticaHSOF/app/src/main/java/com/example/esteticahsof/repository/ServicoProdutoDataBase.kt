package com.example.esteticahsof.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.esteticahsof.model.ServicoProduto

@Database(entities = [ServicoProduto::class], version = 1)
abstract class ServicoProdutoDataBase: RoomDatabase() {

    abstract fun getServicoProdutoDAO(): ServicoProdutoDAO

    companion object {

        private lateinit var INSTANCE: ServicoProdutoDataBase


        fun getInstance(context: Context): ServicoProdutoDataBase {

            if(!::INSTANCE.isInitialized) {
                INSTANCE = Room.databaseBuilder(context, ServicoProdutoDataBase::class.java, "servico_produto_db")
                    .allowMainThreadQueries()
                    .build()
            }
            return INSTANCE
        }

    }

}