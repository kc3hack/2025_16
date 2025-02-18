package com.example.test.data.converter

import androidx.room.TypeConverter
import java.util.Date

class TaskConverters {
    // Date を Long に変換
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}