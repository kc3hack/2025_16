package com.example.test.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.test.data.converter.TaskConverters
import com.example.test.data.model.SleepTimeModel

@Database(entities = [SleepTimeModel::class], version = 1)
@TypeConverters(TaskConverters::class)  // TypeConverter を適用
abstract class SleepedTimeDatabase : RoomDatabase(){
    abstract fun SleepTimeDao(): SleepTimeDao
}