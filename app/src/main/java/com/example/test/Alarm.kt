package com.example.test

import android.app.AlarmManager
import android.content.Intent
import android.os.Build
import android.provider.Settings
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

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

@Composable
private fun hhScrollBoxes(onHourSelected: (hour: Int) -> Unit, selectedHour: Int?) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    val itemHeightPx = with(LocalDensity.current) { 30.dp.toPx() }

    Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(0.dp),
            modifier =
                    Modifier.background(color = Color(0xFF252B26))
                            .size(width = 100.dp, height = 138.dp)
                            .drawWithContent {
                                drawContent()
                                val strokeWidth = 2.dp.toPx()
                                drawLine(
                                        color = Color(0xFF8B8B8B),
                                        start = Offset(0f, 0f),
                                        end = Offset(size.width + 50, 0f),
                                        strokeWidth = strokeWidth
                                )
                                drawLine(
                                        color = Color(0xFF8B8B8B),
                                        start = Offset(0f, size.height),
                                        end = Offset(size.width + 50, size.height),
                                        strokeWidth = strokeWidth
                                )
                            }
                            .verticalScroll(scrollState)
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        for (i in 0..12) {
            Text(
                    text = String.format("%02d", i),
                    modifier = Modifier.padding(top = 15.dp),
                    textAlign = TextAlign.Center,
                    color = if (selectedHour == i) Color.White else Color.Gray,
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.inter_24pt_semibold))
            )
        }
        LaunchedEffect(scrollState.value) {
            val index = ((scrollState.value / (scrollState.maxValue / 13)).toFloat() + 0.5f).toInt()
            if (index != selectedHour) {
                onHourSelected(index)
                //                Toast.makeText(context, "Hour selected: $index",
                // Toast.LENGTH_SHORT).show()
            }
        }
    }
}

@Composable
private fun mmScrollBoxes(onMinuteSelected: (minute: Int) -> Unit, selectedMinute: Int?) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    val itemHeightPx = with(LocalDensity.current) { 30.dp.toPx() }

    Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(0.dp),
            modifier =
                    Modifier.background(color = Color(0xFF252B26))
                            .size(width = 100.dp, height = 138.dp)
                            .drawWithContent {
                                drawContent()
                                val strokeWidth = 2.dp.toPx()
                                drawLine(
                                        color = Color(0xFF8B8B8B),
                                        start = Offset(0f, 0f),
                                        end = Offset(size.width + 50, 0f),
                                        strokeWidth = strokeWidth
                                )
                                drawLine(
                                        color = Color(0xFF8B8B8B),
                                        start = Offset(0f, size.height),
                                        end = Offset(size.width + 50, size.height),
                                        strokeWidth = strokeWidth
                                )
                            }
                            .verticalScroll(scrollState)
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        for (i in 0..59) {
            Text(
                    text = String.format("%02d", i),
                    modifier = Modifier.padding(top = 15.dp),
                    textAlign = TextAlign.Center,
                    color = if (selectedMinute == i) Color.White else Color.Gray,
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.inter_24pt_semibold))
            )
        }
        LaunchedEffect(scrollState.value) {
            val index = ((scrollState.value / (scrollState.maxValue / 60)).toFloat() + 0.5f).toInt()
            if (index != selectedMinute) {
                onMinuteSelected(index)
                //                Toast.makeText(context, "Minute selected: $index",
                // Toast.LENGTH_SHORT).show()
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
            modifier =
                    Modifier.size(width = 70.dp, height = 138.dp)
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
                modifier =
                        Modifier.padding(16.dp).clickable {
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
                modifier =
                        Modifier.padding(bottom = 14.dp, start = 16.dp, end = 16.dp).clickable {
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
    var viewModel = viewModel<TimeViewModel>()
    val context = LocalContext.current
    val hour = viewModel.hour.value
    val minute = viewModel.minute.value
    Column(
            modifier = Modifier.fillMaxSize().background(color = Color.Black),
            horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(80.dp))

        Box(
                modifier =
                        Modifier.size(width = 343.dp, height = 346.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(color = Color(0xFF252B26))
                                .align(Alignment.CenterHorizontally)
        ) {
            if (!buttonClicked) {
                Text(
                        text = "アラームをセット",
                        fontFamily = FontFamily(Font(R.font.inter_24pt_semibold)),
                        fontSize = 20.sp,
                        color = Color.White,
                        modifier = Modifier.padding(top = 18.dp, start = 15.dp)
                )
                Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Box(
                            modifier =
                                    Modifier.size(width = 300.dp, height = 138.dp).offset(y = 70.dp)
                    ) {
                        val hour by viewModel.hour.observeAsState()
                        val minute by viewModel.minute.observeAsState()
                        val isAm by viewModel.isAm.observeAsState()

                        Row(Modifier.fillMaxWidth()) {
                            hhScrollBoxes(
                                    onHourSelected = { hour -> viewModel.setHour(hour) },
                                    selectedHour = hour
                            )
                            Text(
                                    ":",
                                    color = Color.White,
                                    fontSize = 20.sp,
                                    fontFamily = FontFamily(Font(R.font.inter_24pt_semibold)),
                                    modifier = Modifier.padding(top = 53.dp)
                            )

                            mmScrollBoxes(
                                    onMinuteSelected = { minute -> viewModel.setMinute(minute) },
                                    selectedMinute = minute
                            )

                            AmPmScrollSelector(
                                    onAmPmSelected = { isAm -> viewModel.setAmPm(isAm) },
                                    isAmSelected = isAm
                            )
                        }
                    }
                }
                Button(
                        onClick = { onSwitch() },
                        modifier =
                                Modifier.size(width = 148.dp, height = 56.dp)
                                        .offset(x = 15.dp, y = 270.dp)
                                        .border(
                                                1.dp,
                                                Color(color = 0xFF4E4E4E),
                                                RoundedCornerShape(12.dp)
                                        )
                                        .clip(RoundedCornerShape(12.dp))
                                        .background(Color(color = 0xFF343835)),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF343835))
                ) { Text(text = "Cancel", color = Color.White, fontSize = 16.sp) }

                Button(
                        onClick = {
                            viewModel.hour.value?.let { hour ->
                                viewModel.minute.value?.let { minute ->
                                    viewModel.isAm.value?.let { isAm ->
                                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                                            if (!context.getSystemService(AlarmManager::class.java)
                                                            .canScheduleExactAlarms()
                                            ) {
                                                // 権限がない場合、ユーザーに設定画面を表示してもらう
                                                val intent =
                                                        Intent(
                                                                Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM
                                                        )
                                                context.startActivity(intent)
                                            } else {
                                                // アラームを設定する処理
                                                SetAlarm(context, hour, minute, isAm)
                                            }
                                        } else {
                                            // Android 12以前では権限が不要
                                            SetAlarm(context, hour, minute, isAm)
                                        }
                                    }
                                }
                            }
                            buttonClicked = true
                        },
                        modifier =
                                Modifier.size(width = 148.dp, height = 56.dp)
                                        .offset(x = 180.dp, y = 270.dp)
                                        .clip(RoundedCornerShape(12.dp))
                                        .background(Color(color = 0xFF349053)),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF349053))
                ) { Text(text = "Save", color = Color.White, fontSize = 16.sp) }
            } else {
                Box(
                        Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center,
                ) {
                    Column(
                            Modifier.fillMaxWidth().padding(top = 40.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(30.dp)
                    ) {
                        Text(
                                text = "アラームは",
                                style = TextStyle(color = Color.White, fontSize = 20.sp)
                        )
                        Text(
                                text =
                                        "${hour.toString().padStart(2,'0')}:${minute.toString().padStart(2,'0')}",
                                style = TextStyle(color = Color.White, fontSize = 40.sp)
                        )
                        Text(
                                text = "に設定されています",
                                style = TextStyle(color = Color.White, fontSize = 20.sp)
                        )
                    }
                }
            }
        }
    }

    Text(
            text = "睡眠口座",
            fontSize = 20.sp,
            color = Color.White,
            fontFamily = FontFamily(Font(R.font.inter_24pt_semibold)),
            modifier = Modifier.padding(start = 20.dp).offset(y = 475.dp)
    )
    Column(
            modifier = Modifier.fillMaxWidth().offset(y = 510.dp),
            horizontalAlignment = Alignment.CenterHorizontally
    ) {
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
    AlarmScreen(onSwitch = {})
}
