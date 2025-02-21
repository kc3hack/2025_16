package com.example.test.utils.addTasks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.lifecycle.lifecycleScope
import com.example.test.data.db .*
import com.example.test.data.model.ScheduleType
import com.example.test.data.model.TaskModel
import kotlinx.coroutines.launch
import java.util.Date
import android.util.Log

class MainActivity : ComponentActivity() {

    private lateinit var tasksDao: TasksDao // クラスメンバとして定義

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = DatabaseManager.getDatabase(this)
        tasksDao = db.TasksDao()


        // テストデータ追加
        addTask(
            title = "Example Task",
            type = ScheduleType.FIXED_TASK, // 適切な Enum を渡す
            startTime = Date(),
            endTime = Date(System.currentTimeMillis() + 3600000), // 1時間後
            intervalTime = 15,
            workedTime = 30,
            remainingWorkTime = 60,
            memo = ""
        )

        fetchTasks()

    }

    private fun addTask(
        title: String?,
        type: ScheduleType?,
        startTime: Date?,
        endTime: Date?,
        intervalTime: Int?,
        workedTime: Int?,
        remainingWorkTime: Int?,
        memo: String?
    ): Boolean {
        val creationDate = Date() // メソッドが呼ばれた瞬間の時間を取得

        // 必須項目チェック
        val isValid = when (type) {
            ScheduleType.FIXED_TASK, ScheduleType.SLEEP ->
                title != null && type != null && startTime != null && endTime != null && memo != null

            ScheduleType.DEADLINED_ASSIGNMENT ->
                title != null && type != null && endTime != null && intervalTime != null &&
                        workedTime != null && remainingWorkTime != null && memo != null

            ScheduleType.NON_DEADLINED_ASSIGNMENT, ScheduleType.FREE_TIME ->
                title != null && type != null && intervalTime != null &&
                        workedTime != null && remainingWorkTime != null && memo != null

            else -> false
        }

        if (!isValid) return false

        lifecycleScope.launch {
            val newTask = TaskModel(
                title = title!!,
                type = type!!,
                startTime = startTime ?: Date(0),
                endTime = endTime ?: Date(0),
                intervalTime = intervalTime ?: 0,
                workedTime = workedTime ?: 0,
                remainingWorkTime = remainingWorkTime ?: 0,
                creationDate = creationDate, // 呼び出し時の時間をセット
                memo = memo!!
            )
            tasksDao.insert(newTask)
        }
        return true
    }

    // データ取得 & ログに出力
    private fun fetchTasks() {
        lifecycleScope.launch {
            val tasks = tasksDao.getAllTasks() // 全データ取得
            for (task in tasks) {
                Log.d("MainActivity", "Task: $task") // 確認用ログ
            }
        }
    }

}