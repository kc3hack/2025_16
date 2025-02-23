package com.example.test.utils.addTasks

import android.content.Context
import android.util.Log
import com.example.test.data.db.DatabaseManager
import com.example.test.data.model.ScheduleType
import com.example.test.data.model.TaskModel
import com.example.test.utils.Controller
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Date

class AddTask(context: Context) {
    private val tasksDao = DatabaseManager.getDatabase(context).TasksDao() // クラスメンバとして定義
    private val coroutineScope = CoroutineScope(Dispatchers.IO) // 別スレッドで処理するためのスコープ

    /** タスクを追加する (非同期処理) */
    fun addTask(
        title: String?,
        type: ScheduleType?,
        startTime: Date?,
        endTime: Date?,
        intervalTime: Int?,
        workedTime: Int?,
        remainingWorkTime: Int?,
        memo: String?,
        callback: (Boolean) -> Unit // 成功/失敗の結果を返すコールバック
    ) {
        val creationDate = Date() // メソッドが呼ばれた瞬間の時間を取得

        // 必須項目チェック
        val isValid = when (type) {
            ScheduleType.FIXED_TASK, ScheduleType.SLEEP ->
                title != null && startTime != null && endTime != null && memo != null

            ScheduleType.DEADLINED_ASSIGNMENT ->
                title != null && endTime != null && intervalTime != null &&
                        workedTime != null && remainingWorkTime != null && memo != null

            ScheduleType.NON_DEADLINED_ASSIGNMENT, ScheduleType.FREE_TIME ->
                title != null && intervalTime != null &&
                        workedTime != null && remainingWorkTime != null && memo != null

            else -> false
        }

        if (!isValid) {
            callback(false) // 必須項目が不足している場合は失敗
            Log.d("addTasks", "必要な条件が足りない")
            Log.d("addTasks","title:$title\nendTime:${endTime.toString()}\nintervalTime:${intervalTime.toString()}\nworkedTime:${workedTime.toString()}\nremainingWorkTime:${remainingWorkTime.toString()}\nmemo${memo.toString()}")
            return
        }

        coroutineScope.launch {
            try {
                val newTask = TaskModel(
                    title = title!!,
                    type = type!!,
                    startTime = startTime ?: Date(0),
                    endTime = endTime ?: Date(0),
                    intervalTime = intervalTime ?: 0,
                    workedTime = workedTime ?: 0,
                    remainingWorkTime = remainingWorkTime ?: 0,
                    creationDate = creationDate,
                    memo = memo!!
                )

                tasksDao.insert(newTask)

                withContext(Dispatchers.Main) {
                    callback(true) // 成功時に true を返す
                    Controller.schedulingDao.update()
                }
            } catch (e: Exception) {
                Log.e("AddTask", e.toString())
                withContext(Dispatchers.Main) {
                    callback(false) // 失敗時に false を返す
                }
            }
        }
    }
}
