package com.example.esteticahsof.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.esteticahsof.model.Cliente

@Database(entities = [Cliente::class], version = 1)
abstract class ClienteDataBase : RoomDatabase() {

    abstract fun getClienteDAO() : ClienteDAO

    companion object {

        private lateinit var INSTANCE: ClienteDataBase

        fun getInstance(context: Context): ClienteDataBase {

            if(!::INSTANCE.isInitialized) {
                INSTANCE = Room.databaseBuilder(context, ClienteDataBase::class.java, "clientes_db")
                    .allowMainThreadQueries()
                    .build()
            }
            return INSTANCE
        }

    }

}