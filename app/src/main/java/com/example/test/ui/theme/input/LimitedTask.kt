package com.example.test.ui.theme.input

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.test.ScreenState
import com.example.test.ScreenViewModel
import com.example.test.data.model.ScheduleType
import com.example.test.utils.Controller
import java.text.SimpleDateFormat
import java.time.format.TextStyle
import java.util.Calendar
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LimitedTask() {
        var name = remember { mutableStateOf("") }
        var taskDetail = remember { mutableStateOf("") }
        var selectedDate = remember { mutableStateOf<Long?>(null) }
        var showModal = remember { mutableStateOf(false) }
        var endTime = remember { mutableStateOf<Pair<Int, Int>?>(null) }
        var showDialEnd = remember { mutableStateOf(false) }
        var taskNeedTime = remember { mutableStateOf("0") }
        var taskWorkTime = remember { mutableStateOf("0") }
        val screenViewModel:ScreenViewModel = viewModel()
        Column(
                Modifier
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
                TextField(
                        value = name.value,
                        colors =
                                TextFieldDefaults.textFieldColors(
                                        containerColor = Color(0xFFF9D981) // 背景色を変更
                                ),
                        onValueChange = { name.value = it },
                        label = { Text("タスク名") },
                        placeholder = { Text("タスク名を入力") },
                        singleLine = true,
                        modifier = Modifier
                                .fillMaxWidth(0.8f)
                                .background(color = Color(0xFFF9D981))
                )
                TextField(
                        value = taskDetail.value,
                        colors =
                                TextFieldDefaults.textFieldColors(
                                        containerColor = Color(0xFFF9D981) // 背景色を変更
                                ),
                        onValueChange = { taskDetail.value = it },
                        label = { Text("タスク詳細") },
                        placeholder = { Text("タスクの詳細を入力") },
                        singleLine = true,
                        modifier = Modifier
                                .fillMaxWidth(0.8f)
                                .background(color = Color(0xFFF9D981))
                )

                OutlinedTextField(
                        value = selectedDate.value?.let { convertMillisToDate(it) } ?: "",
                        onValueChange = {},
                        label = { Text("予定日") },
                        placeholder = { Text("MM/DD/YYYY") },
                        trailingIcon = {
                                Icon(Icons.Default.DateRange, contentDescription = "Select date")
                        },
                        modifier =
                                Modifier
                                        .fillMaxWidth(0.8f)
                                        .pointerInput(selectedDate) {
                                                awaitEachGesture {
                                                        // Modifier.clickable doesn't work for text fields,
                                                        // so we use
                                                        // Modifier.pointerInput
                                                        // in the Initial pass to observe events before the
                                                        // text field
                                                        // consumes them
                                                        // in the Main pass.
                                                        awaitFirstDown(pass = PointerEventPass.Initial)
                                                        val upEvent =
                                                                waitForUpOrCancellation(
                                                                        pass = PointerEventPass.Initial
                                                                )
                                                        if (upEvent != null) {
                                                                showModal.value = true
                                                        }
                                                }
                                        }
                )
                if (showModal.value) {
                        DatePickerModal(
                                onDateSelected = { selectedDate.value = it },
                                onDismiss = { showModal.value = false }
                        )
                }

                Box(
                        Modifier
                                .fillMaxWidth(0.8f)
                                .clip(RoundedCornerShape(20.dp))
                                .background(color = Color(0xFFF9D981))
                                .padding(start = 40.dp, end = 40.dp, top = 10.dp),
                        contentAlignment = Alignment.Center
                ) {
                        Column(Modifier.fillMaxWidth()) {
                                Text(
                                        text = "締切日",
                                        style =
                                                androidx.compose.ui.text.TextStyle(
                                                        fontSize = 13.sp,
                                                        color = Color(0xFF49454F)
                                                ),
                                        modifier = Modifier.padding(bottom = 10.dp)
                                )
                                DialUseStateExample(
                                        onDismiss = { showDialEnd.value = false },
                                        onConfirm = { time ->
                                                endTime.value = Pair(time.hour, time.minute)
                                                Log.d("endTime", "endTime: $endTime")
                                        }
                                )
                        }
                }

                TextField(
                        value = taskNeedTime.value,
                        colors =
                                TextFieldDefaults.textFieldColors(
                                        containerColor = Color(0xFFF9D981) // 背景色を変更
                                ),
                        onValueChange = { taskNeedTime.value = it },
                        label = { Text("工数") },
                        placeholder = { Text("タスクにかかる時間を入力(整数)") },
                        singleLine = true,
                        modifier = Modifier
                                .fillMaxWidth(0.8f)
                                .background(color = Color(0xFFF9D981))
                )

                TextField(
                        value = taskWorkTime.value,
                        colors =
                                TextFieldDefaults.textFieldColors(
                                        containerColor = Color(0xFFF9D981) // 背景色を変更
                                ),
                        onValueChange = { taskWorkTime.value = it },
                        label = { Text("一回の作業時間") },
                        placeholder = { Text("一度の作業の最大の作業時間を設定(時間)") },
                        singleLine = true,
                        modifier = Modifier
                                .fillMaxWidth(0.8f)
                                .background(color = Color(0xFFF9D981))
                )

                TextButton(
                        onClick = { /*ここに値を渡す設定を追加 */
                                Log.d(
                                        "addTasks",
                                        "selectedDate:${selectedDate.value.toString()}\nendTime:${endTime.value.toString()}\nshowDialEnd:${showDialEnd.value.toString()}"
                                )
                                selectedDate.value?.let { dateMillis ->
                                        val calendar = Calendar.getInstance().apply {
                                                timeInMillis = dateMillis // `selectedDate` から日付を取得
                                                set(Calendar.SECOND, 0)
                                                set(Calendar.MILLISECOND, 0)
                                        }
                                        // endTime の時・分を適用
                                        val endDate: Date? = endTime.value?.let { (hour, minute) ->
                                                val endCalendar = calendar.clone() as Calendar
                                                endCalendar.apply {
                                                        set(Calendar.HOUR_OF_DAY, hour)
                                                        set(Calendar.MINUTE, minute)
                                                }
                                                endCalendar.time // 修正ポイント
                                        }
                                        Controller.addTask.addTask(
                                                title = name.value,
                                                type = ScheduleType.DEADLINED_ASSIGNMENT,
                                                startTime = null,
                                                endTime = endDate,
                                                intervalTime = taskNeedTime.value.toIntOrNull()
                                                        ?: 30,
                                                workedTime = 0,
                                                remainingWorkTime = taskNeedTime.value.toIntOrNull()
                                                        ?: 0,
                                                memo = taskDetail.value,
                                        )
                                        { success ->
                                                if (success) {
                                                        Log.d("addTasks", "タスク追加成功")
                                                        screenViewModel.navigateTo(ScreenState.Second)
                                                } else {
                                                        Log.e("addTasks", "タスク追加失敗")
                                                }
                                        }
                                }
                        },
                        modifier =
                                Modifier
                                        .clip(RoundedCornerShape(27.dp))
                                        .width(100.dp)
                                        .height(50.dp)
                                        .background(color = Color(0xFFFFBB00))
                ) {
                        Text(
                                text = "設定",
                                style =
                                        androidx.compose.ui.text.TextStyle(
                                                fontSize = 17.sp,
                                        )
                        )
                }
                Spacer(Modifier.height(30.dp))
        }
}