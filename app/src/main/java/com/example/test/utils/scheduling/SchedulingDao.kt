package com.example.test.utils.scheduling

import android.content.Context
import com.example.test.data.db.*
import com.example.test.data.model.TaskModel
import com.example.test.utils.scheduleing.TaskTime
import java.util.Date
import java.util.Locale

class SchedulingDao {
    private lateinit var db: AppDatabase
    private lateinit var tasksDao: TasksDao
    private var table = Table()

    val dateFormatToHour = java.text.SimpleDateFormat("HH:mm", Locale.getDefault())// 時間部分を文字列（例："HH:mm"）として取得する用

    fun update(context: Context) {
        db = DatabaseManager.getDatabase(context)
        tasksDao = db.TasksDao()
        table.setSchedules(tasksDao.getAllTasks())
    }

    fun getTaskTimeLine(): List<TaskTime> {
        return table.getTaskTimeLine()
    }

    fun getCalculateTimeLine(): List<TaskTime> {
        table.calculateTimeLine()
        return table.getTaskTimeLine()
    }

    //[タスクの開始時刻(String),終了時刻(String),task名(String),詳細(String)]のリストを返す
    fun getDayTasks(): List<Array<String>>{
        val returnList = mutableListOf<Array<String>>()
        val dayTasks = table.getDayTasks(Date()).filter { it.id!=-1 }
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
