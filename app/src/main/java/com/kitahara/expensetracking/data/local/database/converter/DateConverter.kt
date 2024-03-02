package com.kitahara.expensetracking.data.local.database.converter

import androidx.room.TypeConverter
import java.util.Date

//Converter for changing Date obj into Long type
class DateConverter {
    @TypeConverter
    fun toDate(timestamp: Long?): Date? {
        return timestamp?.let { Date(it) }
    }

    @TypeConverter
    fun toTimestamp(date: Date?): Long? {
        return date?.time
    }
}