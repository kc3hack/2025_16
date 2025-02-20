package com.example.test.ui.theme.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.test.R
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
    var calendarYear = YearMonth.now().getYear()
    var calendarMonth = YearMonth.now().getMonth().toString().lowercaseFirstChar()
    val state =
            rememberCalendarState(
                    startMonth = startMonth,
                    endMonth = endMonth,
                    firstVisibleMonth = currentMonth,
                    firstDayOfWeek = daysOfWeek.first()
            )
    Column(
            Modifier.background(color = Color.White),
            horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(-30.dp)
    ) {
        Spacer(Modifier.height(50.dp))
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            IconButton(
                    onClick = {
                        //
                    },
                    modifier =
                            Modifier.border(
                                    width = 2.dp,
                                    color = Color(0xFFCED3DE),
                                    shape = RoundedCornerShape(10.dp)
                            )
            ) {
                Icon(
                        imageVector =
                                ImageVector.vectorResource(
                                        id = R.drawable.baseline_arrow_back_ios_new_24
                                ),
                        contentDescription = "Localized description",
                )
            }
            Spacer(Modifier.width(50.dp))
            Text(
                    text = "$calendarMonth",
                    style = TextStyle(fontSize = 23.sp, fontWeight = FontWeight.SemiBold)
            )
            Spacer(Modifier.width(50.dp))

            IconButton(
                    onClick = {
                        //
                    },
                    modifier =
                            Modifier.border(
                                    width = 2.dp,
                                    color = Color(0xFFCED3DE),
                                    shape = RoundedCornerShape(10.dp)
                            )
            ) {
                Icon(
                        imageVector =
                                ImageVector.vectorResource(
                                        id = R.drawable.baseline_arrow_forward_ios_24
                                ),
                        contentDescription = "Localized description",
                )
            }
        }
        Spacer(Modifier.height(38.dp))
        Text(text = "$calendarYear", style = TextStyle(fontSize = 13.sp, color = Color(0xFF8F9BB3)))
        Spacer(Modifier.height(85.dp))
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
