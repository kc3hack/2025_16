package com.example.test.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "tasks")
data class TaskModel(
    @PrimaryKey(autoGenerate = true) val id: Int = 0, // 自動採番
    val title: String,         // 作業名
    val type: ScheduleType,             // 予定タイプ
    val startTime: Date,       // 開始時刻（TypeConverterが必要）
    val endTime: Date,         // 終了時刻（TypeConverterが必要）
    val intervalTime: Int,     // 作業の区切る時間
    var workedTime: Int,       // 作業した時間
    var remainingWorkTime: Int,// 残りの作業工数
    val creationDate: Date     // 作成日（TypeConverterが必要）
)
