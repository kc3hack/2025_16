package com.example.test.ui.theme.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.test.ui.theme.ToDoList
import com.example.test.ui.theme.calendar.*
import com.kizitonwose.calendar.compose.HorizontalCalendar
import com.kizitonwose.calendar.compose.rememberCalendarState
import com.kizitonwose.calendar.core.daysOfWeek
import java.time.DayOfWeek
import java.time.YearMonth

@Composable
fun CalenderScreen(onNavigateBack: () -> Unit) {
    val currentMonth = remember { YearMonth.now() }
    val startMonth = remember { currentMonth.minusMonths(100) } // Adjust as needed
    val endMonth = remember { currentMonth.plusMonths(100) } // Adjust as needed
    val daysOfWeek = remember { daysOfWeek(firstDayOfWeek = DayOfWeek.SUNDAY) }

    val state =
            rememberCalendarState(
                    startMonth = startMonth,
                    endMonth = endMonth,
                    firstVisibleMonth = currentMonth,
                    firstDayOfWeek = daysOfWeek.first()
            )
    Column(Modifier.background(color = Color.White)) {
        HorizontalCalendar(
                state = state,
                dayContent = { Day(it) },
                monthHeader = {
                    DaysOfWeekTitle(daysOfWeek = daysOfWeek) // Use the title as month header
                }
        )
        ToDoList(modifier = Modifier)
    }
}
