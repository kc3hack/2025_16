package com.example.test.data.model

enum class ScheduleType(val typeNumber: Int) {
    /**
     * 固定タスクを表します。
     *
     * @property FIXED_TASK 固定スケジュールのタスク。
     * @param typeNumber 固定タスクの一意の識別子。
     */
    FIXED_TASK(1),
    /**
     * 睡眠を表します。
     *
     * @property SLEEP 睡眠のタスク。
     * @param typeNumber 睡眠の一意の識別子。
     */
    SLEEP(2),
    /**
     * 締め切りのある課題を表します。
     *
     * @property DEADLINED_ASSIGNMENT 締め切りのある課題のタスク。
     * @param typeNumber 締め切りのある課題の一意の識別子。
     */
    DEADLINED_ASSIGNMENT(3),
    /**
     * 締め切りのない課題を表します。
     *
     * @property NON_DEADLINED_ASSIGNMENT 締め切りのない課題のタスク。
     * @param typeNumber 締め切りのない課題の一意の識別子。
     */
    NON_DEADLINED_ASSIGNMENT(4),
    /**
     * フリーの時間を表します。
     *
     * @property FREE_TIME フリーの時間のタスク。
     * @param typeNumber フリーの時間の一意の識別子。
     */
    FREE_TIME(5)
}