package com.example.test.data.converter

import androidx.room.TypeConverter
import com.example.test.data.model.ScheduleType
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

    // ScheduleType を String に変換
    @TypeConverter
    fun fromScheduleType(value: ScheduleType): String {
        return value.name
    }

    @TypeConverter
    fun toScheduleType(value: String): ScheduleType {
        return enumValueOf(value)
    }
}