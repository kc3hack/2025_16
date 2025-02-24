package com.example.test.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task_time_table")
data class TaskTimeEntity(
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,  // 一意の ID
    val id: Int,
    val startTime: Long,  // Date は Long (ミリ秒) に変換
    val endTime: Long
)
