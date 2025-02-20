package com.example.test.utils.scheduleing

import com.example.test.data.model.*
import java.util.Date
import java.util.Locale

class ScheduleCalculation() {
    /**
     * タスクのリスト
     */
    private var scheduleList: MutableList<TaskModel> = mutableListOf()
    /**
     * TaskTimeを保存するリスト
     */
    private val tasks: MutableList<TaskTime> = mutableListOf()
    /**
     * 現在時刻(嘘)
     */
    private val currentTime = Date()
    /**
     * 空き時間のid
     */
    private val freeTimeId = -1
    /**
     * 睡眠時間のid
     */
    private val sleepTimeId = -2
    /**
     * Date型のフォーマット
     */
    val dateFormatToDay = java.text.SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())// 日付部分を文字列（例："2025/02/17"）として取得する用
    val dateFormat = java.text.SimpleDateFormat("yyyy/MM/dd HH:mm", Locale.getDefault())// 日付と時間を組み合わせるよう
    /**
     * 指定した時刻のTaskTimeを二分探索で調べ、その番号を返す
     */
    fun findTaskByTime(time: Date, priority: Int /* 0が前、1が後 */): Int {
        if (tasks.isEmpty()) {
            return -1 // あるいは例外を投げる等の処理
        }
        if (tasks.size == 1) {
            // その1件が時刻に合致しているかを確認することも検討する
            return 0
        }
        var low = 0
        var high = tasks.size - 1
    
        while (low <= high) {
            val mid = (low + high) / 2
            val midTask = tasks[mid]
    
            // タスクの期間内に time があるかをチェック（通常は startTime < time < endTime）
            if (midTask.startTime.before(time) && midTask.endTime.after(time)) {
                return mid
            } else if (priority == 0 && midTask.startTime.equals(time)) {
                return mid
            } else if (priority == 1 && midTask.endTime.equals(time)) {
                return mid
            }
            
            // 昇順ソートの場合：startTime が time より前なら、リストの後半（より古いもの）を探索
            if (midTask.startTime.after(time)) {
                high = mid - 1
            } else {
                low = mid + 1
            }
        }
        return -1 // 見つからなかった場合
    }
    
    /**
     * tasksから、期間を指定して抽出
     * その際、タスクの間であっても強制的に切る
     */
    fun getTasksWithinPeriod(startTime: Date, endTime: Date): List<TaskTime> {
        val startIndex = findTaskByTime(startTime,0)
        val endIndex = findTaskByTime(endTime,1)
        if (startIndex == -1 || endIndex == -1 || startIndex > endIndex) {
            return emptyList()
        }
        val returnTasks = mutableListOf<TaskTime>()
        for (i in startIndex..endIndex) {
            returnTasks.add(tasks[i])
        }
        returnTasks[0] = returnTasks[0].copy(startTime = startTime)
        returnTasks[returnTasks.size - 1] = returnTasks[returnTasks.size - 1].copy(endTime = endTime)
        return returnTasks
    }

    /**
     * tasksから、期間を指定して抽出
     * その際、タスクの途中であればそれも入れる。
     */
    fun getTasksWithinPeriodNoCut(startTime: Date, endTime: Date): List<TaskTime> {
        val startIndex = findTaskByTime(startTime,0)
        val endIndex = findTaskByTime(endTime,1)
        if (startIndex == -1 || endIndex == -1 || startIndex > endIndex) {
            return emptyList()
        }
        val returnTasks = mutableListOf<TaskTime>()
        for (i in startIndex..endIndex) {
            returnTasks.add(tasks[i])
        }
        return returnTasks
    }

    /**
     * タスクを登録する際、idが-1のTaskを分断し、間にtaskが入るようにする。
     */
    fun addTasks(task: TaskTime): Boolean {
        val startIndex = findTaskByTime(task.startTime,0)
        val endIndex = findTaskByTime(task.endTime,1)
        if(startIndex==-1||endIndex==-1){
            // println("無効な時間設定")
            // println(task)
            return false
        }
        if(startIndex == endIndex && tasks[startIndex].id==-1){
            val preTasks: MutableList<TaskTime> = mutableListOf()
            if(tasks[startIndex].startTime.before(task.startTime)) {
                preTasks.add(TaskTime(-1,tasks[startIndex].startTime,task.startTime))
            }
            preTasks.add(TaskTime(task.id,task.startTime,task.endTime))
            if(task.endTime.before(tasks[startIndex].endTime)) {
                preTasks.add(TaskTime(-1,task.endTime,tasks[startIndex].endTime))
            }
            tasks.removeAt(startIndex)
            tasks.addAll(startIndex, preTasks)
            return true
        }else{
            // println("他の予定と重なっています")
            // println(task)
        }
        return false
    }

    /**
     * 固定のタスクを入れる
     */
    fun setFixedTask() {
        for (schedule in scheduleList) {
            if (schedule.type == ScheduleType.FIXED_TASK) {
                addTasks(TaskTime(schedule.id, schedule.startTime, schedule.endTime))
            }
        }
    }

    /**
     * 移動可能タスクと、睡眠時間を生成し、入れる。
     * 睡眠と干渉しない時間にまずタスクを入れる。
     * 
     */
    fun setSleepTimeAndAssignment() {
        var assignments = scheduleList
            .filter { it.type == ScheduleType.DEADLINED_ASSIGNMENT }
            .sortedBy { it.endTime }
    
        val remainingWorkTimeSum = assignments.sumOf { it.remainingWorkTime.toLong() }
    
        val taskStartTime = currentTime
        val scheduleRangeDays = SettingData().scheduleRange
        val scheduleEndTime = Date(currentTime.time + scheduleRangeDays * 24 * 60 * 60 * 1000)
    
        val freeTimes = getTasksWithinPeriod(taskStartTime, scheduleEndTime).filter { it.id == -1 }
        val freeTimeSum = freeTimes.sumOf { it.endTime.time - it.startTime.time }
    
        // println("Remaining Work Time Sum: ${remainingWorkTimeSum}分")
        // println("Free Time Sum: ${freeTimeSum / (1000 * 60)}分")

        for (i in 0 until SettingData().scheduleRange) {
            val dayStartTime = Date(currentTime.time + i * 24 * 60 * 60 * 1000)
            assignments = setAssignmentInNoom(currentTime,assignments.toMutableList())
            assignments = setSleepTask(dayStartTime, assignments.toMutableList())
        }
    }
    

    /**
     * 寝ない時間帯にタスクを入れる
     */
    fun setAssignmentInNoom(startTime: Date, assignments: MutableList<TaskModel>): MutableList<TaskModel> {
        val sleepStartTime = SettingData().sleepStartTime // "HH:mm" 形式（例："22:00"）の文字列
    
        val formattedStartDay = dateFormatToDay.format(startTime)
    
        val endTime = dateFormat.parse("$formattedStartDay $sleepStartTime") ?: startTime
    
        // もしすでに startTime が寝る時間以降なら、処理せずそのまま返す
        if (startTime.after(endTime)) {
            return assignments
        }
        var returnAssignments = assignments
        // startTime～endTime の期間内の空き時間（id == -1）を取得
        val freeTimeInPeriod = getTasksWithinPeriod(startTime, endTime).filter { it.id == -1 }
    
        // 各空き時間に対してタスクを割り当てる
        for (freeTime in freeTimeInPeriod) {
            returnAssignments = setAssignmentInFreeTime(freeTime,returnAssignments)
        }
        return returnAssignments
    }

    /**
     * 次の日までのタスクがある場合、それを終了してから寝る。
     */
    fun setSleepTask(startTime: Date, assignments: MutableList<TaskModel>): MutableList<TaskModel> {
        val nextDay = Date(startTime.time + 24 * 60 * 60 * 1000)
        var dayTasks = assignments.filter { it.endTime.before(nextDay) }
        if (dayTasks.isEmpty()) {
            addSleep(startTime)
            return assignments
        }else {
            val dayFreeTimes = getTasksWithinPeriod(startTime,nextDay).filter{ it.id == freeTimeId }
            var dayTasksCopy = dayTasks.toMutableList()
            for (freeTime in dayFreeTimes) {
                dayTasksCopy = setAssignmentInFreeTime(freeTime, dayTasksCopy)
            }
            addSleep(startTime)
            val assignmentsCopy = assignments.toMutableList()
            for (dayTask in dayTasks) {
                if (dayTasksCopy.none { it.id == dayTask.id }) {
                    assignmentsCopy.removeIf { it.id == dayTask.id }
                }else {
                    val updatedTask = dayTasksCopy.first { it.id == dayTask.id }
                    assignmentsCopy[assignmentsCopy.indexOfFirst { it.id == dayTask.id }] = updatedTask
                }
            }
            return assignmentsCopy
        }
    }

    fun addSleep(startTime: Date) {
        // println("addSleep")
        // println(startTime)
    
        val nextDay = Date(startTime.time + 24 * 60 * 60 * 1000)
        val sleepStartTime = dateFormat.parse("${dateFormatToDay.format(startTime)} ${SettingData().sleepStartTime}")
        val sleepEndTime = dateFormat.parse("${dateFormatToDay.format(nextDay)} ${SettingData().sleepEndTime}")
    
        // println("Sleep Start Time: $sleepStartTime")
        // println("Sleep End Time: $sleepEndTime")
    
        val sleepTimeTasks = getTasksWithinPeriod(sleepStartTime, sleepEndTime)
        val longestFreeTime = sleepTimeTasks.filter { it.id == -1 }
            .maxByOrNull { it.endTime.time - it.startTime.time }
    
        longestFreeTime?.let {
        val sleepDuration = SettingData().sleepDuration * 60 * 1000
        val sleepEndTimeAdjusted = Date(it.startTime.time + sleepDuration)
        addTasks(TaskTime(sleepTimeId, it.startTime, sleepEndTimeAdjusted.coerceAtMost(sleepEndTime)))
        }
    
        // println(longestFreeTime)
    }
    

    /**
     * 指定した空き時間に、タスクを入れられる限り入れる。
     */
    fun setAssignmentInFreeTime(freeTime: TaskTime, assignments: MutableList<TaskModel>): MutableList<TaskModel> {
        val breakTime = SettingData().breakTime  // 単位は「分」と仮定

        // 空き時間の開始時刻に breakTime 分の休憩時間を加えた時刻から開始
        var preTime = Date(freeTime.startTime.time + breakTime * 60 * 1000)
            
        while (preTime.before(freeTime.endTime)) {
            var scheduled = false
            // ループ中にタスクを割り当てるため、assignments の各要素を順次処理
            for (i in assignments.indices) {
                val assignment = assignments[i]
                // タスクの1区切りの作業時間（分）をミリ秒に変換
                val taskDurationMillis = assignment.intervalTime * 60 * 1000

                // 割り当て開始時刻 + タスク作業時間が、現在の空き時間内に収まるなら
                if (preTime.time + taskDurationMillis <= freeTime.endTime.time) {
                    val taskEndTime = Date(preTime.time + taskDurationMillis)
                    // タスクを追加する処理（実装済みの addTasks 関数を呼び出す）
                    addTasks(TaskTime(assignment.id, preTime, taskEndTime))

                    // 次のタスク開始時刻は、現在のタスク終了後に breakTime 分休憩を加えた時刻
                    preTime = Date(taskEndTime.time + breakTime * 60 * 1000)

                    // 残り作業時間を減らす（ここでは intervalTime 分ずつ減算）
                    assignment.remainingWorkTime -= assignment.intervalTime

                    // 作業が完了したら、リストから削除
                    if (assignment.remainingWorkTime <= 0) {
                        assignments.removeAt(i)
                    }
                    scheduled = true
                    break  // 割り当てたら、while ループ内で次の preTime へ
                }
            }
            // もしどのタスクも割り当てられなかった場合、while ループを抜ける
            if (!scheduled) break
        }
        return assignments
    }
    

    /**
     * タスクを計算し、返す
     */
    fun getTasks(): List<TaskTime> {
        return tasks
    }

    fun updateTaskTimeList(scheduleList: MutableList<TaskModel>): List<TaskTime> {
        this.scheduleList = scheduleList
        val tenYearsLater = Date(currentTime.time + 10L * 365 * 24 * 60 * 60 * 1000)
        tasks.add(TaskTime(-1, currentTime, tenYearsLater))
        setFixedTask()
        setSleepTimeAndAssignment()
        return tasks
    }
}