package com.example.test.utils.userStatus

import android.content.Context
import com.example.test.data.db.AppDatabase
import com.example.test.data.db.DatabaseManager
import com.example.test.data.db.SleepTimeDao
import com.example.test.data.db.SleepedTimeDatabase
import com.example.test.data.db.TasksDao
import com.example.test.data.model.ScheduleType
import com.example.test.data.model.SleepTimeModel
import com.example.test.utils.userStatios.SharedPreferencesHelper
import java.util.Date
import java.util.concurrent.TimeUnit

class SleepManager(context: Context) {
    private var sharedPrefs: SharedPreferencesHelper = SharedPreferencesHelper(context)
    private var db: SleepedTimeDatabase = DatabaseManager.getSleepedTimeDatabase(context)
    private var sleepTimeDao: SleepTimeDao = db.SleepTimeDao()

    // 睡眠開始時間を保存
    fun setSleepStartTime() {
        sharedPrefs.saveLastSleepDate(Date())
    }

    fun wakeUp() {
        if (sharedPrefs.getStatus() == ScheduleType.SLEEP) {
            val sleepStart = sharedPrefs.getLastSleepDate() // 保存された睡眠開始時間
            val wakeUpTime = Date() // 現在時刻

            // 睡眠時間（ミリ秒）を算出し、分単位に変換
            val sleepDuration = TimeUnit.MILLISECONDS.toMinutes(wakeUpTime.time - sleepStart.time)

            // Roomに保存
            val sleepRecord = SleepTimeModel(
                startSleepDate = sleepStart,
                sleepTime = sleepDuration
            )
            sleepTimeDao.insert(sleepRecord)

            // 状態を通常に戻す
            sharedPrefs.saveStatus(ScheduleType.FREE_TIME)
        }
    }
}
