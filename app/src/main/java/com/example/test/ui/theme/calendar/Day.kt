package com.example.test.ui.theme.calendar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.DayPosition

@Composable
fun Day(day: CalendarDay) {
    Box(
            modifier = Modifier.aspectRatio(1f), // This is important for square sizing!
            contentAlignment = Alignment.Center
    ) {
        Text(
                text = day.date.dayOfMonth.toString(),
                color =
                        if (day.position == DayPosition.MonthDate) Color(0xFF222B45)
                        else Color(0xFF8F9BB3),
                fontWeight = FontWeight.SemiBold
        )
    }
}
