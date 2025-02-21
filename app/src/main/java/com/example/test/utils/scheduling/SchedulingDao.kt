package com.example.test.utils.scheduling

import android.content.Context
import com.example.test.data.db.*
import com.example.test.data.model.TaskModel
import com.example.test.data.model.TaskTimeEntity
import com.example.test.utils.scheduleing.TaskTime
import java.util.Date
import java.util.Locale

class SchedulingDao(context: Context) {
    private var db = DatabaseManager.getDatabase(context)
    private var tasksDao = db.TasksDao()
    private var timeTableDb = DatabaseManager.getTaskTimeDatabase(context)
    private var timeTableDao = timeTableDb.taskTimeTableDao()
    private var table = Table()

    private val dateFormatToHour = java.text.SimpleDateFormat("HH:mm", Locale.getDefault())// 時間部分を文字列（例："HH:mm"）として取得する用
    private val dateFormatToDay = java.text.SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())// 時間部分を文字列（例："HH:mm"）として取得する用

    init {
        table.setSchedules(tasksDao.getAllTasks())
        table.setTaskTimeLine(timeTableDao.getAllTasks().map {
            TaskTime(id = it.id, startTime = Date(it.startTime), endTime = Date(it.endTime))
        })
    }

    /**
     * データベースのtasksから、スケジュールの再計算、データベースに保存
     */
    suspend fun update() {
        table.setSchedules(tasksDao.getAllTasks())
        table.calculateTimeLine()
        timeTableDao.deleteAll()
        timeTableDao.insertAll(table.getTaskTimeLine().map {
            TaskTimeEntity(id = it.id, startTime = it.startTime.time, endTime = it.endTime.time)
        })
    }

    fun getTaskTimeLine(): List<TaskTime> {
        return table.getTaskTimeLine()
    }

//    fun getCalculateTimeLine(): List<TaskTime> {
//        table.calculateTimeLine()
//        return table.getTaskTimeLine()
//    }

    //[タスクの開始時刻(String),終了時刻(String),task名(String),詳細(String)]のリストを返す
    fun getDayTasks(day:String = ""): List<Array<String>>{
        val returnList = mutableListOf<Array<String>>()
        var dayDate = dateFormatToHour.parse(day)
        if (dayDate == null){
            dayDate = dateFormatToDay.parse(dateFormatToDay.format(Date()))
        }
        val dayTasks = table.getDayTasks(dayDate).filter { it.id!=-1 }
        for (dayTask in dayTasks){
            val startTime = dateFormatToHour.format(dayTask.startTime)
            val endTime = dateFormatToHour.format(dayTask.endTime)
            var taskName = "不明なタスク"
            var detail = "詳細なし"
            if (dayTask.id == -2){
                taskName = "睡眠"
                detail = "猫と一緒に寝ましょう"
            }else{
                val task = tasksDao.getTaskById(dayTask.id)
                if(task is TaskModel){
                    taskName = task.title
                }
            }
            returnList.add(arrayOf(startTime,endTime,taskName,detail))
        }
        return returnList
    }

    /**
     * 指定した日のタスクの数を調べる
     */
    fun getDayTasksCount(): Int{
        return getDayTasks().size
    }
    /**
     * タスクを行った時間を返す
     */
    fun endTasksTime() :Int {
        val tasks = tasksDao.getAllTasks()
        var time = 0
        for (task in tasks){
            time += task.workedTime
        }
        return time
    }
}
