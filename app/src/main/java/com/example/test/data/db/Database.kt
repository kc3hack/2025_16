package com.example.test.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.test.data.converter.TaskConverters
import com.example.test.data.model.TaskModel

@Database(entities = [TaskModel::class], version = 1)
@TypeConverters(TaskConverters::class)  // TypeConverter を適用
abstract class AppDatabase : RoomDatabase() {
    abstract fun TasksDao(): TasksDao
}
