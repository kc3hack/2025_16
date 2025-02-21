package com.example.test.utils.scheduling

import com.example.test.data.model.*
import com.example.test.utils.scheduleing.ScheduleCalculation
import com.example.test.utils.scheduleing.TaskTime
import java.util.Date

class Table {
    /**
     * スケジュールのリストのフィールド  
     * このリストは TaskModel オブジェクトを保持するために使用されます。
     */
    private var scheduleList: MutableList<TaskModel> = mutableListOf()
    private var scheduleCalculation = ScheduleCalculation()
    private var taskTimeLine: List<TaskTime> = mutableListOf()

    /**
     * スケジュールを追加するメソッド  
     * 新しいスケジュールをリストに追加します。
     */
    fun addSchedules(schedules: List<TaskModel>) {
        scheduleList.addAll(schedules)
    }

    fun setSchedules(schedules: List<TaskModel>) {
        this.scheduleList = schedules.toMutableList()
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
        return taskTimeLine
    }

    fun setTaskTimeLine(taskTimeTable: List<TaskTime>) {
        scheduleCalculation.setTasks(taskTimeTable)
        this.taskTimeLine = taskTimeTable
    }

    /**
     * スケジュール計算  
     * ScheduleCalculation クラスを利用して、スケジュールを計算・抽出します。
     */
    fun calculateTimeLine() {
        val TasksList = scheduleList.filter { !it.isEnd }.toMutableList()
        this.taskTimeLine = scheduleCalculation.updateTaskTimeList(TasksList)
    }

    /**
     * 24時間のタスクを返す
     */
    fun getDayTasks(startTime: Date): List<TaskTime> {
        val endTime = Date(startTime.time + 1L * 24 * 60 * 60 * 1000) // 24時間（ミリ秒単位）
        return scheduleCalculation.getTasksWithinPeriodNoCut(startTime, endTime)
    }
}
