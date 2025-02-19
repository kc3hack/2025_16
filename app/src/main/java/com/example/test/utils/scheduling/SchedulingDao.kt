package com.example.test.utils.scheduling

import android.content.Context
import com.example.test.data.db.*
import com.example.test.utils.scheduleing.Table
import com.example.test.utils.scheduleing.TaskTime

class SchedulingDao {
    private lateinit var db: AppDatabase
    private lateinit var tasksDao: TasksDao
    private var table = Table()

    fun update(context: Context) {
        db = DatabaseManager.getDatabase(context)
        tasksDao = db.TasksDao()
        table.setSchedules(tasksDao.getAllTasks())
    }

    fun getTaskTimeLine(): List<TaskTime> {
        return table.getTaskTimeLine()
    }

    fun getCalculateTimeLine(): List<TaskTime> {
        return table.calculateTimeLine()
    }
}
