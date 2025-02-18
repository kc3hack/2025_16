package com.example.test.data.db

import android.content.Context
import androidx.room.Room

object DatabaseManager {
    private var INSTANCE: AppDatabase? = null

    fun getDatabase(context: Context): AppDatabase {
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "tasks"
            ).allowMainThreadQueries()
                .build()
            INSTANCE = instance
            instance
        }
    }
}
