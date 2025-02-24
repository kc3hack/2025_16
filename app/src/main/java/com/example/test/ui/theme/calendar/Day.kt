package com.example.test.ui.theme.calendar

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.test.utils.Controller
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.DayPosition
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Locale

@Composable
fun Day(day: CalendarDay) {
        val dayToString = day.date.toString().replace("-","/")
        Log.d("getDayTasksCount",dayToString)
        val eventCount= Controller.schedulingDao.getDayTasksCount(dayToString)
        Box(
                modifier =
                        Modifier.aspectRatio(1f)
                                .clickable { Log.d("TAG", day.date.toString()) }
                                , // This is important for square sizing!
                contentAlignment = Alignment.Center
        ) {
                Box(
                        Modifier.clip(RoundedCornerShape(10.dp)).background(
                                        color =
                                                (if (day.date == LocalDate.now()) Color(0xFF735BF2)
                                                else Color.White)
                                )
                                .size(30.dp)
                                .align(Alignment.Center)
                ) {
                        Text(
                                text = day.date.dayOfMonth.toString(),
                                color =
                                        (if (day.date == LocalDate.now()) Color.White
                                        else
                                                (if (day.position == DayPosition.MonthDate)
                                                        Color(0xFF222B45)
                                                else Color(0xFF8F9BB3))),
                                fontWeight = FontWeight.SemiBold,modifier = Modifier.align(Alignment.Center)
                        )
                }

                if (eventCount > 0) {
                Spacer(Modifier.height(50.dp))
                EventDots(eventCount)
        }

        }
}

@Composable
fun EventDots(eventCount: Int) {
        Row(
                
                modifier = Modifier.width(30.dp).padding(top=43.dp),horizontalArrangement = Arrangement.Center
                 // 丸が並ぶ幅を制限
        ) {
                repeat(eventCount.coerceAtMost(3)) {
                        index ->
                        if (index > 0) {
                                Spacer(modifier = Modifier.width(4.dp)) // 丸同士のスペースを8dpに設定
                        }// 最大3つの丸を描画
                        Canvas(modifier = Modifier.size(4.dp)) {
                                drawCircle(color = Color(0xFF735BF2)) // 丸の色
                        }
                }
        }
}
