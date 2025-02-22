package com.example.test.utils

import android.content.Context
import com.example.test.utils.scheduling.SchedulingDao
import com.example.test.utils.userStatios.CatLevels
import com.example.test.utils.userStatus.SleepManager

object Controller {
    private lateinit var schedulingDao: SchedulingDao
    private lateinit var sleepManager: SleepManager

    val dao: SchedulingDao get() = schedulingDao
    val manager: SleepManager get() = sleepManager
    val catLevels: CatLevels by lazy { CatLevels(schedulingDao, sleepManager) }

    fun init(context: Context) {
        schedulingDao = SchedulingDao(context)
        sleepManager = SleepManager(context)
    }
}
