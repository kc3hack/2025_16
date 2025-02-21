package com.example.test.ui.theme.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.test.ui.theme.home.*

@Composable
fun SleepInfo(modifier: Modifier = Modifier) {
    val sleepTime: Float = (5).toFloat()
    val sleepCount = String.format("%.1f", sleepTime)
    Column(
            modifier = Modifier.fillMaxWidth().padding(2.dp),
            verticalArrangement = Arrangement.spacedBy(0.dp),
            horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MakeGraph(modifier = Modifier, sleepTime)
        Box(
                Modifier.background(
                                color = Color(0x99F9D981),
                                shape =
                                        RoundedCornerShape(
                                                topStart = 8.dp,
                                                bottomEnd = 8.dp,
                                                topEnd = 8.dp,
                                                bottomStart = 8.dp
                                        )
                        )
                        .fillMaxWidth(0.97f)
                        .padding(top = 8.dp, bottom = 8.dp)
                        .align(Alignment.CenterHorizontally)
        ) {
            Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                if (sleepTime < 0) {
                    Text("必要睡眠時間: ${sleepCount}時間")
                } else {
                    Text("必要睡眠時間: 0時間")
                }
                Text(
                        text = "おめでとう！この調子でねこたちに貢ぎ続けましょう！",
                        style = TextStyle(fontWeight = FontWeight.Light, fontSize = 13.sp)
                )
            }
        }
    }
}
