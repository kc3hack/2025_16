package com.example.test
import android.app.AlarmManager
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.Calendar

@Composable
fun TimePickerDialog(
    context: Context,
    onTimeSelected: (hour: Int, minute: Int) -> Unit
) {
    val currentTime = Calendar.getInstance()
    val hour = currentTime.get(Calendar.HOUR_OF_DAY)
    val minute = currentTime.get(Calendar.MINUTE)

    TimePickerDialog(
        context,
        { _, selectedHour: Int, selectedMinute: Int ->
            onTimeSelected(selectedHour, selectedMinute)
        },
        hour,
        minute,
        true
    ).show()
}
/*class TimeViewModel : ViewModel() {
    private val _time = MutableLiveData<Pair<Int, Int>>()
    val time: LiveData<Pair<Int, Int>> = _time

    fun setTime(hour: Int, minute: Int) {
        _time.value = Pair(hour, minute)
    }
}

@Composable
fun SetAlarm(context: Context, viewModel: TimeViewModel) {
    val time by viewModel.time.observeAsState()
    time?.let { (hour, minute) ->
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        val calendar = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, hour)
            set(Calendar.MINUTE, minute)
            set(Calendar.SECOND, 0)
        }

        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)
    }
}

*/
class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        // アラームが鳴った時のアクションをここに実装
        Toast.makeText(context, "アラームが鳴りました！", Toast.LENGTH_SHORT).show()
    }
}
/* <receiver android:name=".AlarmReceiver" /> をAndroidManifest.xmlに入れる*/