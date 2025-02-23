package com.example.test.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.test.ui.theme.*
import com.example.test.utils.Controller
import java.time.format.TextStyle

@Composable
fun ToDoList(modifier: Modifier = Modifier) {
    val expBar: Float = (0.5).toFloat()
    Column(
            modifier.fillMaxWidth().padding(top = 23.dp),
            horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(
                    text = "24時間以内のタスク",
                    style =
                            androidx.compose.ui.text.TextStyle(
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 20.sp
                            )
            )
            //            Spacer(modifier = Modifier.height(20.dp))
            LinearProgressIndicator(
                    progress = expBar,
                    color = Color(0xFF006B5F),
                    trackColor = Color(0xFFDAE5E1),
                    modifier =
                            Modifier.height(80.dp)
                                    .width(280.dp)
                                    .padding(top = 65.dp)
                                    .clip(RoundedCornerShape(8.dp))
            )
        }
        val tasks = Controller.schedulingDao.getDayTasks()
        tasks.forEach { task -> ToDoBox(Modifier, task[0], task[1], task[2], task[3]) }

        Spacer(Modifier.padding(top = 10.dp))
        ToDoSleep(Modifier, "22:00", "7:00")
    }
}