package com.example.test.data.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

object DatabaseManager {
    private var TASKS_INSTANCE: AppDatabase? = null
    private var SLEEPED_TIME_INSTANCE: SleepedTimeDatabase? = null
    private var TASK_TIMES_INSTANCE: TaskTimeTableDatabase? = null

    fun getDatabase(context: Context): AppDatabase {
        return TASKS_INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "tasks"
            ).allowMainThreadQueries()
                .build()
            TASKS_INSTANCE = instance
            instance
        }
    }

    fun getSleepedTimeDatabase(context: Context): SleepedTimeDatabase {
        return SLEEPED_TIME_INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                SleepedTimeDatabase::class.java,
                "sleeped_times"
            ).allowMainThreadQueries()
                .build()
            SLEEPED_TIME_INSTANCE = instance
            instance
        }
    }

    fun getTaskTimeDatabase(context: Context): TaskTimeTableDatabase {
        return TASK_TIMES_INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                TaskTimeTableDatabase::class.java,
                "task_time_table"
            ).allowMainThreadQueries()
                .build()
            TASK_TIMES_INSTANCE = instance
            instance
        }
    }
}
