package com.example.test
import android.app.TimePickerDialog
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx. compose. material3.Text
import androidx. compose. material3.Button
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx. compose. foundation. background
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx. compose. ui. platform. LocalContext
import androidx. compose. ui. graphics. Color
import androidx.compose.material3.IconButton
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx. compose. foundation. rememberScrollState
import androidx. compose. foundation. verticalScroll
import java.util.*
import kotlin.coroutines.jvm.internal.*
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.BroadcastReceiver
import android.widget.Toast
import android. widget. NumberPicker
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx. compose. ui. tooling. preview. Preview


@Composable
fun NumberPicker(value: Int, onValueChange: (Int) -> Unit, range: IntRange) {
    var selectedValue by remember { mutableIntStateOf(value) }

    Row {
        IconButton(onClick = {
            if (selectedValue > range.first) {
                selectedValue--
                onValueChange(selectedValue)
            }
        }) {
            Text(text = "-", fontSize = 24.sp)
        }
        Text(text = selectedValue.toString(), modifier = Modifier.padding(16.dp))
        IconButton(onClick = {
            if (selectedValue < range.last) {
                selectedValue++
                onValueChange(selectedValue)
            }
        }) {
            Icon(Icons.Filled.Add, contentDescription = "Increase")
        }
    }
}


@Composable
public fun testAlarmScreen(onTimeSelected: (hour: Int, minute: Int) -> Unit) {
    val screenState = remember { mutableStateOf(ScreenState.Third) }
    var hour by remember { mutableIntStateOf(0) }
    var minute by remember { mutableIntStateOf(0) }

    Column {
        Text(text = "Set Alarm")
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            NumberPicker(
                value = hour,
                onValueChange = { hour = it },
                range = 0..23
            )
            Spacer(modifier = Modifier.width(16.dp))
            NumberPicker(
                value = minute,
                onValueChange = { minute = it },
                range = 0..59
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { onTimeSelected(hour, minute) }) {
            Text(text = "Set Alarm")
        }
    }
}

fun testAlarm(context: Context, hour: Int, minute: Int) {
    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    val intent = Intent(context, AlarmReceiver::class.java)
    val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0)

    val calendar = Calendar.getInstance().apply {
        set(Calendar.HOUR_OF_DAY, hour)
        set(Calendar.MINUTE, minute)
        set(Calendar.SECOND, 0)
    }

    alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)
}
class testAlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        // アラームが鳴った時の処理をここに記述
        Toast.makeText(context, "Alarm ringing!", Toast.LENGTH_SHORT).show()
    }
}

@Composable
private fun ScrollBoxes() {
    Column(
        modifier = Modifier
            .background(Color.LightGray)
            .size(100.dp)
            .verticalScroll(rememberScrollState())
    ) {
        repeat(10) {
            Text("Item $it", modifier = Modifier.padding(2.dp))
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewAlarmScreen() {
    testAlarmScreen { hour, minute -> Unit }}



