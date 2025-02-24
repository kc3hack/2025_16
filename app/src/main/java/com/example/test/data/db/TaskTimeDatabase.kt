package com.example.test.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.test.data.model.TaskTimeEntity

@Database(entities = [TaskTimeEntity::class], version = 1, exportSchema = false)
abstract class TaskTimeTableDatabase : RoomDatabase() {
    abstract fun taskTimeTableDao(): TaskTimeTableDao
}
