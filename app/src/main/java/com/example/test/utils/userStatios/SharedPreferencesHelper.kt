package com.example.test.utils.userStatios

import android.content.Context
import android.content.SharedPreferences
import com.example.test.data.model.ScheduleType
import java.util.Date

class SharedPreferencesHelper(context: Context) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)

    companion object {
        private const val KEY_LAST_SLEEP_DATE = "key_last_sleep_date"
        private const val KEY_STATUS = "key_status"
    }

    // lastSleepDate（Date）を保存
    fun saveLastSleepDate(date: Date) {
        prefs.edit().putLong(KEY_LAST_SLEEP_DATE, date.time).apply()
    }

    // lastSleepDate（Date）を取得
    fun getLastSleepDate(): Date {
        val time = prefs.getLong(KEY_LAST_SLEEP_DATE, -1)
        return Date(time)
    }

    // status（ScheduleType）を保存
    fun saveStatus(status: ScheduleType) {
        prefs.edit().putString(KEY_STATUS, status.name).apply()
    }

    // status（ScheduleType）を取得
    fun getStatus(): ScheduleType? {
        val statusName = prefs.getString(KEY_STATUS, null)
        return statusName?.let { ScheduleType.valueOf(it) }
    }
}
