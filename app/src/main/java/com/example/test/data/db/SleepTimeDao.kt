package com.example.test.data.db

import androidx.room.*
import com.example.test.data.model.SleepTimeModel

@Dao
interface SleepTimeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(sleepTime: SleepTimeModel)

    @Query("SELECT * FROM sleepTimes ORDER BY startSleepDate DESC")
    fun getAllSleepTimes(): List<SleepTimeModel>

    @Delete
    fun delete(sleepTime: SleepTimeModel)
}
