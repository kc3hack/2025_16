package com.example.test

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.lifecycle.lifecycleScope
import com.example.test.data.db.DatabaseManager
import com.example.test.data.model.TaskModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Date

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 親のレイアウトを作成 (縦方向の LinearLayout)
        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(32, 32, 32, 32)
        }

        // TextView を動的に作成
        val tvTasks = TextView(this).apply {
            text = "Loading..."
            textSize = 18f
        }

        // レイアウトに TextView を追加
        layout.addView(tvTasks)

        // 作成したレイアウトをアクティビティのビューに設定
        setContentView(layout)

        // Room データベースの処理を実行
        addTaskTest(tvTasks)
    }

    private fun addTaskTest(tvTasks: TextView) {
        val db = DatabaseManager.getDatabase(this)
        val tasksDao = db.TasksDao()

        lifecycleScope.launch(Dispatchers.IO) { // Room の操作はバックグラウンドスレッドで実行
            // タスクの作成
            val task = TaskModel(
                title = "空き時間",
                id = -1,
                type = 0,
                startTime = Date(),
                endTime = Date(),
                intervalTime = 15,
                workedTime = 0,
                remainingWorkTime = 60,
                creationDate = Date()
            )

            // データを挿入
            val taskId = tasksDao.insert(task)

            // ID で取得
            if (taskId is Int) {
                val retrievedTask = tasksDao.getTaskById(taskId)

                // retrievedTask が null でない場合、削除処理
                if (retrievedTask != null) {
                    tasksDao.delete(retrievedTask)
                }
            }

            // 全データ取得
            val allTasks = tasksDao.getAllTasks()

            // UI スレッドで TextView に結果を表示
            launch(Dispatchers.Main) {
                // allTasks の内容をテキストとして表示
                tvTasks.text = allTasks.joinToString("\n") { it.title }
            }
        }
    }
}
