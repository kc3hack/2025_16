package com.example.test.data.db

import android.content.Context
import androidx.room.Room

object DatabaseManager {
    private var TASKS_INSTANCE: AppDatabase? = null
    private var SLEEPED_TIME_INSTANCE: SleepedTimeDatabase? = null

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
}
