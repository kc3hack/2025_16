package com.example.test
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.FabPosition
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.IconButton
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.*
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.fontResource
import androidx.compose.ui.zIndex
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.graphics.PathFillType
import android.graphics.drawable.shapes.Shape
import android.util.Size
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlinx.coroutines.delay
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx. compose. foundation. pager. rememberPagerState
import androidx. compose. foundation. pager. HorizontalPager
import androidx.compose.material3.HorizontalDivider
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx. compose. ui. draw. drawWithContent
import androidx.compose.ui.geometry.Rect
import androidx. compose. ui. geometry. Offset
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.platform.LocalDensity

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.verticalScroll
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import android.app.TimePickerDialog
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx. compose. foundation. clickable
import java.util.Calendar



class TimeViewModel : ViewModel() {
    private val _hour = MutableLiveData<Int>()
    private val _minute = MutableLiveData<Int>()

    val hour: LiveData<Int> = _hour
    val minute: LiveData<Int> = _minute

    fun setHour(hour: Int) {
        _hour.value = hour
    }

    fun setMinute(minute: Int) {
        _minute.value = minute
    }
}

@Composable
fun SetAlarm(context: Context, viewModel: TimeViewModel) {
    val hour by viewModel.hour.observeAsState()
    val minute by viewModel.minute.observeAsState()

    hour?.let { h ->
        minute?.let { m ->
            // アラームを設定するコード
            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val intent = Intent(context, AlarmReceiver::class.java)
            val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

            val calendar = Calendar.getInstance().apply {
                set(Calendar.HOUR_OF_DAY, h)
                set(Calendar.MINUTE, m)
                set(Calendar.SECOND, 0)
            }

            alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)
            Toast.makeText(context, "アラームがセットされました", Toast.LENGTH_SHORT).show()
        }
    }
}
/*@Composable
fun MainComponent() {
    val context = LocalContext.current
    val viewModel = viewModel<TimeViewModel>()

    Column {
        hhScrollBoxes { hour -> viewModel.setHour(hour) }
        mmScrollBoxes { minute -> viewModel.setMinute(minute) }
        SetAlarm(context, viewModel)
    }
}
*/


@Composable
private fun hhScrollBoxes(onHourSelected: (hour: Int) -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(0.dp),
        modifier = Modifier
            .background(color = Color(0xFF252B26))
            .size(width = 100.dp, height = 50.dp)
            .drawWithContent {
                drawContent()
                val strokeWidth = 2.dp.toPx()
                drawLine(
                    color = Color(0xFF8B8B8B),
                    start = Offset(0f, 0f),
                    end = Offset(size.width, 0f),
                    strokeWidth = strokeWidth
                )
                drawLine(
                    color = Color(0xFF8B8B8B),
                    start = Offset(0f, size.height),
                    end = Offset(size.width, size.height),
                    strokeWidth = strokeWidth
                )
            }
            .verticalScroll(rememberScrollState())
    ) {
        for (i in 0..23) {
            Text(
                    text = i.toString(),
                modifier = Modifier.padding(top = 15.dp,bottom = 15.dp)
                    .clickable { onHourSelected(i) }
                ,textAlign = TextAlign.Center,
                    color = Color.White,
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.inter_24pt_semibold))
                )

        }
    }
}
@Composable
private fun mmScrollBoxes(onMinuteSelected: (minute: Int) -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp),
        modifier = Modifier
            .offset(x = 100.dp)
            .background(color = Color(0xFF252B26))
            .size(width = 100.dp, height = 50.dp)
            .drawWithContent {
                drawContent()
                val strokeWidth = 2.dp.toPx()
                drawLine(
                    color = Color(0xFF8B8B8B),
                    start = Offset(0f, 0f),
                    end = Offset(size.width, 0f),
                    strokeWidth = strokeWidth
                )
                drawLine(
                    color = Color(0xFF8B8B8B),
                    start = Offset(0f, size.height),
                    end = Offset(size.width, size.height),
                    strokeWidth = strokeWidth
                )
            }
            .verticalScroll(rememberScrollState())

    ) {
        for (i in 0..59) {
            Text(
                text = i.toString(),
                modifier = Modifier.padding(top = 15.dp, bottom = 15.dp)
                .clickable { onMinuteSelected(i) },
                textAlign = TextAlign.Center,
                color = Color.White,
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.inter_24pt_semibold))
            )

        }
    }
}



@Composable
public fun AlarmScreen(onSwitch: () -> Unit) {
    var buttonClicked by remember { mutableStateOf(false) }
    var viewModel = TimeViewModel()
    val context = LocalContext.current
    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.height(80.dp))
        Box(
            modifier = Modifier
                .size(width = 343.dp, height = 343.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(color = Color(0xFF252B26))
                .align(alignment = Alignment.CenterHorizontally)
        ) {
            Text(
                text = "アラームをセット",
                fontFamily = FontFamily(Font(R.font.inter_24pt_semibold)),
                fontSize = 20.sp,
                color = Color.White,
                modifier = Modifier.padding(top = 18.dp, start = 15.dp)
            )
            Column(modifier = Modifier.fillMaxWidth()
                , horizontalAlignment = Alignment.CenterHorizontally) {
                Box(
                    modifier = Modifier
                        .size(width = 202.dp, height = 56.dp)
                        .offset(y = 120.dp)

                )

                {
                    hhScrollBoxes { hour -> viewModel.setHour(hour) }
                    mmScrollBoxes{ minute -> viewModel.setMinute(minute) }
                    Text(":",
                    color = Color.White,
                        fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.inter_24pt_semibold)),
                    modifier = Modifier.align(Alignment.Center)
                        .padding(bottom = 5.dp))


                }
            }
            Button(
                onClick = { onSwitch() },
                modifier = Modifier
                    .size(width = 148.dp, height = 56.dp)
                    .offset(x = 15.dp, y = 270.dp)
                    .border(1.dp, Color(color = 0xFF4E4E4E), RoundedCornerShape(12.dp))
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(color = 0xFF343835))
                    ,
                colors = ButtonDefaults.buttonColors( containerColor = Color(0xFF343835))

            ) {
                Text(
                    text = "Cancel",
                    color = Color.White,
                    fontSize = 16.sp
                )
            }

            Button(
                onClick = {
                    SetAlarm(context, viewModel)
                    buttonClicked = true },
                modifier = Modifier
                    .size(width = 148.dp, height = 56.dp)
                    .offset(x = 180.dp, y = 270.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(color = 0xFF349053)),
                colors = ButtonDefaults.buttonColors( containerColor = Color(0xFF349053))

            ) {
                Text(
                    text = "Save",
                    color = Color.White,
                    fontSize = 16.sp
                )

            }

        }

    }

        Text(
            text = "睡眠口座",
            fontSize = 20.sp,
            color = Color.White,
            fontFamily = FontFamily(Font(R.font.inter_24pt_semibold)),
            modifier = Modifier
                .padding(start = 20.dp)
                .offset(y = 475.dp)
        )
    Column(modifier = Modifier
        .fillMaxWidth()
        .offset(y = 510.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    )

    {
        Text(
            text = "+02:00",
            color = Color(0xFF8AFF5C),
            fontSize = 96.sp,
            fontFamily = FontFamily(Font(R.font.inter_24pt_extralight)),
        )

        Text(
            text = "とてもよく眠れています！",
            color = Color.White,
            fontSize = 20.sp,
            fontFamily = FontFamily(Font(R.font.inter_24pt_semibold)),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 45.dp)
        )
    }




}


@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
fun AlarmScreenPreview() {
    AlarmScreen(
        onSwitch = {}
    )

}

