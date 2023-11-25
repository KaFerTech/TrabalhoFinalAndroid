package com.example.esteticahsof.utils

import androidx.room.TypeConverter
import java.sql.Time
import java.text.SimpleDateFormat
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