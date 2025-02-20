package com.example.test.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "sleepTimes")
data class SleepTimeModel (
    @PrimaryKey(autoGenerate = true) val id: Int = 0, // 自動採番
    val startSleepDate: Date, //睡眠開始時間
    val sleepTime: Long //睡眠時間
)