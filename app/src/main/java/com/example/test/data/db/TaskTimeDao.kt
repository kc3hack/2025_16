package com.example.test.data.db

import androidx.room.*
import com.example.test.data.model.TaskTimeEntity

@Dao
interface TaskTimeTableDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(tasks: List<TaskTimeEntity>) // すべて置き換える

    @Query("DELETE FROM task_time_table")
    suspend fun deleteAll() // 既存のデータを削除

    @Query("SELECT * FROM task_time_table")
    fun getAllTasks(): List<TaskTimeEntity>
}
