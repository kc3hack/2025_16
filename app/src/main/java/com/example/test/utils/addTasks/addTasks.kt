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
            creationDate = Date()
        )

        fetchTasks()

    }

        // データ追加
        private fun addTask(
            title: String,         // 作業名
            type: ScheduleType,             // 予定タイプ
            startTime: Date,       // 開始時刻（TypeConverterが必要）
            endTime: Date,         // 終了時刻（TypeConverterが必要）
            intervalTime: Int,     // 作業の区切る時間
            workedTime: Int,       // 作業した時間
            remainingWorkTime: Int,// 残りの作業工数
            creationDate: Date     // 作成日（TypeConverterが必要）
        ) {
            lifecycleScope.launch {
                val currentTime = System.currentTimeMillis()
                val newTask = TaskModel(
                    title = title,
                    type = type,
                    startTime = startTime,
                    endTime = endTime,
                    intervalTime = intervalTime,
                    workedTime = workedTime,
                    remainingWorkTime = remainingWorkTime,
                    creationDate = creationDate
                )
                tasksDao.insert(newTask)
            }
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