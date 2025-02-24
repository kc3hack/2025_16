package com.example.test.utils.userStatios

import android.content.Context
import com.example.test.utils.scheduling.SchedulingDao
import com.example.test.utils.userStatus.SleepManager

class CatLevels (schedulingDao:SchedulingDao, sleepManager:SleepManager){
    private val requiredTimeWorkCat = 100
    private val requiredTimeSleepCat = 420

    private val schedulingDao = schedulingDao
    private val sleepManager = sleepManager

    fun getWorkCatLevel(): Int {
        return schedulingDao.endTasksTime() / requiredTimeWorkCat
    }

    fun getSleepCatLevel(): Int{
        return (sleepManager.getSleepTimeSum() / requiredTimeSleepCat).toInt()
    }

    fun getNextRequiredTimeWorkCat(): Long{
        return (schedulingDao.endTasksTime() % requiredTimeWorkCat).toLong()
    }

    fun getNextRequiredTimeSleepCat(): Long{
        return sleepManager.getSleepTimeSum() % requiredTimeSleepCat
    }
}