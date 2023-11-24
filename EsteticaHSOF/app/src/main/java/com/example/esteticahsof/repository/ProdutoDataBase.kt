package com.example.esteticahsof.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.esteticahsof.model.Produto

@Database(entities = [Produto::class], version = 1)
abstract class ProdutoDataBase : RoomDatabase() {

    abstract fun getDAO(): ProdutoDAO

    companion object {

        private lateinit var INSTANCE: ProdutoDataBase

        fun getInstance(context: Context): ProdutoDataBase {

            if(!::INSTANCE.isInitialized) {
                INSTANCE = Room.databaseBuilder(context, ProdutoDataBase::class.java, "produtos_db")
                    .allowMainThreadQueries()
                    .build()
            }
            return INSTANCE
        }

    }

}