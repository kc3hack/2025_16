package com.example.test.utils

import android.content.Context
import com.example.test.utils.addTasks.AddTask
import com.example.test.utils.scheduling.SchedulingDao
import com.example.test.utils.userStatios.CatLevels
import com.example.test.utils.userStatus.SleepManager

object Controller {
    private lateinit var SchedulingDao: SchedulingDao
    private lateinit var SleepManager: SleepManager
    private lateinit var AddTask: AddTask

    val schedulingDao: SchedulingDao get() = SchedulingDao
    val sleepManager: SleepManager get() = SleepManager
    val addTask: AddTask get() = AddTask
    val catLevels: CatLevels by lazy { CatLevels(schedulingDao, sleepManager) }

    fun init(context: Context) {
        SchedulingDao = SchedulingDao(context)
        SleepManager = SleepManager(context)
        AddTask = AddTask(context)
    }
}
