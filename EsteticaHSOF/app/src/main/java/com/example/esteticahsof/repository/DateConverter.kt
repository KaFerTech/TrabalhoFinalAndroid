package com.example.esteticahsof.repository

import androidx.room.TypeConverter
import java.util.Date

class DateConverter {

    companion object {

        @TypeConverter
        @JvmStatic
        fun toDate(value: Long?): Date? {
            return value?.let { Date(it) }
        }

        @TypeConverter
        @JvmStatic
        fun toLong(value: Date?): Long? {
            return value?.time
        }

        // Adicione outros métodos de conversão conforme necessário
    }
}