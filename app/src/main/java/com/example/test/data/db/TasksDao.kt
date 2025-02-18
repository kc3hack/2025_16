package com.example.test.data.db

import androidx.room.*
import com.example.test.data.model.TaskModel

@Dao
interface TasksDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(task: TaskModel)

    @Query("SELECT * FROM tasks WHERE id = :id")
    fun getTaskById(id: Int): TaskModel?

    @Query("SELECT * FROM tasks")
    fun getAllTasks(): List<TaskModel>

    @Delete
    fun delete(task: TaskModel)
}
