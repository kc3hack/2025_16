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
import androidx. compose. material3.Slider
import androidx. compose. material3.SliderDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx. compose. foundation. clickable
import androidx. compose. foundation. ScrollState
import java.util.Calendar



class TimeViewModel : ViewModel() {
    private val _hour = MutableLiveData<Int>()
    private val _minute = MutableLiveData<Int>()
    private val _isAm = MutableLiveData<Boolean>()

    val hour: LiveData<Int> = _hour
    val minute: LiveData<Int> = _minute
    val isAm: LiveData<Boolean> = _isAm

    fun setHour(hour: Int) {
        _hour.value = hour
    }
    fun setMinute(minute: Int) {
        _minute.value = minute
    }
    fun setAmPm(isAm: Boolean) {
        _isAm.value = isAm
    }
}
fun SetAlarm(context: Context, hour: Int, minute: Int, isAm: Boolean)  {
    val hour24 = if (isAm){
        if (hour == 12) 0 else hour
    } else {
        if (hour == 12) 12 else hour + 12
    }
    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    val intent = Intent(context, AlarmReceiver::class.java)
    val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

            val calendar = Calendar.getInstance().apply {
                set(Calendar.HOUR_OF_DAY, hour24)
                set(Calendar.MINUTE, minute)
                set(Calendar.SECOND, 0)
            }

            alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)
            Toast.makeText(context, "アラームがセットされました", Toast.LENGTH_SHORT).show()
}
@Composable
private fun hhScrollBoxes(onHourSelected: (hour: Int) -> Unit, selectedHour: Int?) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    val itemHeight = 48.dp
    val itemHeightPx = with(LocalDensity.current) { itemHeight.toPx() }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(0.dp),
        modifier = Modifier
            .background(color = Color(0xFF252B26))
            .size(width = 100.dp, height = 138.dp)
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
            .verticalScroll(scrollState)
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        for (i in 0..12) {
            Text(
                text = String.format("%02d", i),
                modifier = Modifier.padding(top = 15.dp)
                    .height(itemHeight)
                    .fillMaxWidth()
                ,textAlign = TextAlign.Center,
                    color = if (selectedHour == i)Color.White else Color.Gray,
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.inter_24pt_semibold)
                    )
                )
        }
        LaunchedEffect(scrollState.value) {
            val index = ((scrollState.value /  + 0.5f).toInt()
            if (index != selectedHour) {
                onHourSelected(index)
                Toast.makeText(context, "Hour selected: $index", Toast.LENGTH_SHORT).show()

            }
        }
    }
}
@Composable
private fun mmScrollBoxes(onMinuteSelected: (minute: Int) -> Unit, selectedMinute: Int?) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    val itemHeightPx = with(LocalDensity.current) { 30.dp.toPx()}

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(0.dp),
        modifier = Modifier
            .offset(x = 100.dp)
            .background(color = Color(0xFF252B26))
            .size(width = 100.dp, height = 138.dp)
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
            .verticalScroll(scrollState)

    ) {
        Spacer(modifier = Modifier.height(40.dp))
        for (i in 0..59) {
            Text(
                text = String.format("%02d",i),
                modifier = Modifier.padding(top = 15.dp)

                ,textAlign = TextAlign.Center,
                color = if (selectedMinute == i)Color.White else Color.Gray,
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.inter_24pt_semibold))
            )
        }
        LaunchedEffect(scrollState.value) {
            val index = ((scrollState.value / (scrollState.maxValue / 60)).toFloat() + 0.5f).toInt()
            if (index != selectedMinute) {
               onMinuteSelected(index)
                Toast.makeText(context, "Minute selected: $index", Toast.LENGTH_SHORT).show()

            }
        }
    }
}

@Composable
fun AmPmScrollSelector(onAmPmSelected: (Boolean) -> Unit, isAmSelected: Boolean?) {
    var isAm by remember { mutableStateOf(isAmSelected == true) }
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .size(width = 70.dp, height = 138.dp)
            .offset(x = 180.dp)
            .background(Color(0xFF252B26))
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
            .verticalScroll(scrollState)
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = "AM",
            color = if (isAmSelected == true) Color.White else Color.Gray,
            fontSize = 20.sp,
            fontFamily = FontFamily(Font(R.font.inter_24pt_semibold)),
            modifier = Modifier
                .padding(16.dp)
                .clickable {
                    isAm = true
                    onAmPmSelected(true)
                }
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = "PM",
            color = if (isAmSelected == false) Color.White else Color.Gray,
            fontSize = 20.sp,
            fontFamily = FontFamily(Font(R.font.inter_24pt_semibold)),
            modifier = Modifier
                .padding(bottom = 14.dp, start = 16.dp, end = 16.dp)
                .clickable {
                    isAm = false
                    onAmPmSelected(false)
                }
        )
        Spacer(modifier = Modifier.height(50.dp))
    }
    LaunchedEffect(scrollState.value) {
        val scrollThreshold = scrollState.maxValue / 2
        val previousIsAm = isAm
        isAm = scrollState.value <= scrollThreshold
        if (previousIsAm != isAm) {
            onAmPmSelected(isAm)
        }
    }
}


@Composable
public fun AlarmScreen(onSwitch: () -> Unit) {
    var buttonClicked by remember { mutableStateOf(false) }
    var viewModel = viewModel <TimeViewModel>()
    val context = LocalContext.current

    Column(
        modifier = Modifier
        .fillMaxSize()
        .background(color = Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.height(80.dp))
        Box(
            modifier = Modifier
                .size(width = 343.dp, height = 346.dp)
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
            Column(
                modifier = Modifier.fillMaxWidth()
                    .size(width = 343.dp, height = 346.dp)
                ,horizontalAlignment = Alignment.CenterHorizontally)
                {
                Box(
                    modifier = Modifier
                        .size(width = 343.dp, height = 346.dp),
                    ) {
                    val hour by viewModel.hour.observeAsState()
                    val minute by viewModel.minute.observeAsState()
                    val isAm by viewModel.isAm.observeAsState()

                    hhScrollBoxes(
                        onHourSelected = { hour -> viewModel.setHour(hour) },
                        selectedHour = hour
                    )
                    mmScrollBoxes(
                        onMinuteSelected = { minute -> viewModel.setMinute(minute) },
                        selectedMinute = minute
                    )
                    AmPmScrollSelector(
                        onAmPmSelected = { isAm -> viewModel.setAmPm(isAm) },
                        isAmSelected = isAm
                    )
                    Text(
                        ":",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontFamily = FontFamily(Font(R.font.inter_24pt_semibold)),
                        modifier = Modifier.offset(x = 100.dp)
                            .padding(top = 53.dp)
                    )

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
                , colors = ButtonDefaults.buttonColors( containerColor = Color(0xFF343835))

            ) {
                Text(
                    text = "Cancel",
                    color = Color.White,
                    fontSize = 16.sp
                )
            }

            Button(
                onClick = {
                    viewModel.hour.value ?.let { hour ->
                        viewModel.minute.value?.let { minute ->
                            viewModel.isAm.value?.let { isAm ->
                                SetAlarm(context, hour, minute, isAm)
                            }
                        }
                    }
                    buttonClicked = true
                          },
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

