package com.example.esteticahsof.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.esteticahsof.model.ProdutoDeServico

@Database(entities = [ProdutoDeServico::class], version = 1)
abstract class ProdutoDeServicoDataBase: RoomDatabase() {

    abstract fun getProdutoDeServicoDAO(): ProdutoDeServicoDAO

    companion object {

        private lateinit var INSTANCE: ProdutoDeServicoDataBase


        fun getInstance(context: Context): ProdutoDeServicoDataBase {

            if(!::INSTANCE.isInitialized) {
                INSTANCE = Room.databaseBuilder(context, ProdutoDeServicoDataBase::class.java, "produto_de_servico_db")
                    .allowMainThreadQueries()
                    .build()
            }
            return INSTANCE
        }

    }

}