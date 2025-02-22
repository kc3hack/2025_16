package com.example.test.ui.theme.input

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Checkbox
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
import java.time.format.TextStyle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Schedule() {
        var name = remember { mutableStateOf("") }
        var taskDetail = remember { mutableStateOf("") }
        var selectedDate = remember { mutableStateOf<Long?>(null) }
        var showModal = remember { mutableStateOf(false) }
        var startTime = remember { mutableStateOf<TimePickerState?>(null) }
        var showDialStart = remember { mutableStateOf(false) }
        var endTime = remember { mutableStateOf<TimePickerState?>(null) }
        var showDialEnd = remember { mutableStateOf(false) }

        var checked = remember { mutableStateOf(false) }

        Column(
                Modifier.fillMaxWidth().verticalScroll(rememberScrollState()),
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
                        modifier = Modifier.fillMaxWidth(0.8f).background(color = Color(0xFFF9D981))
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
                        modifier = Modifier.fillMaxWidth(0.8f).background(color = Color(0xFFF9D981))
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
                                Modifier.fillMaxWidth(0.8f).pointerInput(selectedDate) {
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
                        Modifier.fillMaxWidth(0.8f)
                                .clip(RoundedCornerShape(20.dp))
                                .background(color = Color(0xFFF9D981))
                                .padding(start = 20.dp, end = 20.dp, top = 10.dp),
                        contentAlignment = Alignment.Center
                ) {
                        Column(Modifier.fillMaxWidth()) {
                                Text(
                                        text = "開始時刻",
                                        style =
                                                androidx.compose.ui.text.TextStyle(
                                                        fontSize = 13.sp,
                                                        color = Color(0xFF49454F)
                                                ),
                                        modifier = Modifier.padding(bottom = 10.dp)
                                )
                                DialUseStateExample(
                                        onDismiss = { showDialStart.value = false },
                                        onConfirm = { time ->
                                                startTime.value = time
                                                showDialStart.value = false
                                        },
                                )
                        }
                }

                Box(
                        Modifier.fillMaxWidth(0.8f)
                                .clip(RoundedCornerShape(20.dp))
                                .background(color = Color(0xFFF9D981))
                                .padding(start = 20.dp, end = 20.dp, top = 10.dp),
                        contentAlignment = Alignment.Center
                ) {
                        Column(Modifier.fillMaxWidth()) {
                                Text(
                                        text = "終了時刻",
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
                                                endTime.value = time
                                                showDialEnd.value = false
                                        },
                                )
                        }
                }

                Box(
                        Modifier.fillMaxWidth(0.8f)
                                .clip(RoundedCornerShape(20.dp))
                                .background(color = Color(0xFFF9D981))
                                .padding(start = 20.dp, end = 20.dp, top = 10.dp),
                        contentAlignment = Alignment.Center
                ) {
                        Row(
                                Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                                Checkbox(
                                        checked = checked.value,
                                        onCheckedChange = { checked.value = it }
                                )
                                Text(
                                        text = "曜日ごとに登録する",
                                        style =
                                                androidx.compose.ui.text.TextStyle(
                                                        fontSize = 13.sp,
                                                ),
                                        modifier = Modifier.padding(top = 12.dp)
                                )
                        }
                }
                TextButton(
                        onClick = { /*ここに値を渡す設定を追加 */},
                        modifier =
                                Modifier.clip(RoundedCornerShape(27.dp))
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
