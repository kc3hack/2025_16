package com.example.test.utils.scheduleing

import com.example.test.data.model.*

class Table {
    /**
     * スケジュールのリストのフィールド  
     * このリストは TaskModel オブジェクトを保持するために使用されます。
     */
    val scheduleList: MutableList<TaskModel> = mutableListOf()
    val scheduleCalculation = ScheduleCalculation()

    /**
     * スケジュールを追加するメソッド  
     * 新しいスケジュールをリストに追加します。
     */
    fun addSchedules(schedules: List<TaskModel>) {
        scheduleList.addAll(schedules)
    }

    /**
     * スケジュールを削除するメソッド  
     * 指定されたスケジュールをリストから削除します。
     */
    fun deleteSchedules(schedules: List<TaskModel>) {
        scheduleList.removeAll(schedules)
    }

    /**
     * スケジュールを取得するメソッド  
     * 現在のスケジュールのリストを返します。
     */
    fun getTasks(): List<TaskModel> {
        return scheduleList.toList()
    }

    fun getTaskTimeLine(): List<TaskTime> {
        return scheduleCalculation.getTasks()
    }

    /**
     * スケジュール計算  
     * ScheduleCalculation クラスを利用して、スケジュールを計算・抽出します。
     *
     * ここでは例として、calculate() メソッドで何らかの処理を行い、結果のリストを返す実装とします。
     */
    fun calculateSchedule(): List<TaskTime> {
        return scheduleCalculation.updateTaskTimeList(scheduleList)
    }
}
