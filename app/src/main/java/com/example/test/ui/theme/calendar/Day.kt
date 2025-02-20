package com.example.test.ui.theme.calendar

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
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
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.DayPosition
import java.time.LocalDate

@Composable
fun Day(day: CalendarDay) {
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
        }
}
