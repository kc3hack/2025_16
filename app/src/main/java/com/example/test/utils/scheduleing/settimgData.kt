package com.example.test.utils.scheduleing

data class SettingData(
    val scheduleRange: Int = 3, // 日
    val breakTime: Int = 15, // 休憩時間 (分)
    val sleepStartTime: String = "22:00", // 入眠時間 (HH:mm)
    val sleepEndTime: String = "08:00", // 入眠時間 (HH:mm)
    val sleepDuration: Int = 420, // 睡眠時間 (分)
)