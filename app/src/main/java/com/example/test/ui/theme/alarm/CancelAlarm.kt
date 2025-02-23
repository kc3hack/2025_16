package com.example.test.ui.theme.alarm

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.widget.Toast

fun cancelAlarm(context: Context) {
    val intent =
            Intent(context, AlarmReceiver::class.java).apply {
                action = "com.example.test.ALARM_TRIGGERED"
            }

    // PendingIntentを作成 (アラーム設定時に使用したものと同じ)
    val pendingIntent =
            PendingIntent.getBroadcast(
                    context,
                    0, // 一意のリクエストコード (設定時と同じコードを使用)
                    intent,
                    PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )

    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    alarmManager.cancel(pendingIntent) // アラームをキャンセル
    Toast.makeText(context, "アラームが解除されました", Toast.LENGTH_SHORT).show()
}
