package com.example.test.ui.theme.alarm

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.Ringtone
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import android.widget.Toast
import com.example.test.utils.Controller
import java.util.Calendar

private var alarmMgr: AlarmManager? = null
private lateinit var alarmIntent: PendingIntent

class AlarmReceiver : BroadcastReceiver() {
    companion object {
        private var ringtone: Ringtone? = null

        fun stopAlarm() {
            Controller.sleepManager.wakeUp()
            ringtone?.takeIf { it.isPlaying }?.stop()
        }
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        if (context != null) {
            playAlarmSound(context)
        }
    }

    private fun playAlarmSound(context: Context) {
        val ringtoneUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
        ringtone = RingtoneManager.getRingtone(context, ringtoneUri)

        ringtone?.let { it.play() }
                ?: run { Toast.makeText(context, "アラーム音を再生できません", Toast.LENGTH_SHORT).show() }
    }
}

fun SetAlarm(context: Context, hour: Int, minute: Int, isAm: Boolean) {

    val hour24 =
            if (isAm) {
                if (hour == 12) 0 else hour
            } else {
                if (hour == 12) 12 else hour + 12
            }

    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    val intent =
            Intent(context, AlarmReceiver::class.java).apply {
                action = "com.example.test.ALARM_TRIGGERED"
            }

    val pendingIntent =
            PendingIntent.getBroadcast(
                    context,
                    0,
                    intent,
                    PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )

    val calendar =
            Calendar.getInstance().apply {
                set(Calendar.HOUR_OF_DAY, hour24)
                set(Calendar.MINUTE, minute)
                set(Calendar.SECOND, 0)
                set(Calendar.MONTH, get(Calendar.MONTH))

                if (timeInMillis <= System.currentTimeMillis()) {
                    add(Calendar.DAY_OF_YEAR, 1)
                }
            }
    Log.d("SetAlarm", "Calendar Time: ${calendar}")
    //    Log.d("SetAlarmMIli", "Calendar Time: ${calendar.timeInMillis}")
    // Android 12以降はsetExactAndAllowWhileIdle()を使う
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        alarmManager.setExactAndAllowWhileIdle(
                AlarmManager.RTC_WAKEUP,
                calendar.timeInMillis,
                pendingIntent
        )
        alarmMgr?.setRepeating(
                AlarmManager.RTC_WAKEUP,
                calendar.timeInMillis,
                1000 * 60 * 20,
                alarmIntent
        )
    } else {
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)
        alarmMgr?.setRepeating(
                AlarmManager.RTC_WAKEUP,
                calendar.timeInMillis,
                1000 * 60 * 20,
                alarmIntent
        )
    }
    Controller.sleepManager.setSleepStartTime()
    Toast.makeText(context, "アラームが設定されました", Toast.LENGTH_SHORT).show()
}
