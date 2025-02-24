package com.example.test.ui.theme

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.test.utils.Controller
import java.time.Duration
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@Composable
fun ToDoBox(
    beginTime: String,
    endTime: String,
    taskName: String,
    description: String,
    id: Int
) {
    var isVisible by remember { mutableStateOf(true) }

    if (isVisible) {
        Box(Modifier.fillMaxWidth().height(75.dp)) {
            Row(Modifier.padding(top = 10.dp)) {
                FloatingActionButton(
                    onClick = {
                        println("clicked!")
                        // beginTimeとendTimeは、HH:mmの形でくるので、それの差分をdidTimeとしたい(日を跨ぐ場合がある)
                        val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")

                        val beginLocalTime = LocalTime.parse(beginTime, timeFormatter)
                        val endLocalTime = LocalTime.parse(endTime, timeFormatter)

                        val durationMinutes = Duration.between(beginLocalTime, endLocalTime).toMinutes()

                        val didTime = if (durationMinutes < 0) durationMinutes + 1440 else durationMinutes

                        Controller.schedulingDao.updateRemainingWorkTime(
                            id,
                            didTime = didTime.toInt(),
                        )
                        isVisible = false
                    },
                    elevation = FloatingActionButtonDefaults.elevation(0.dp),
                    modifier = Modifier.background(color = Color.White)
                ) {
                    Canvas(
                        modifier = Modifier.size(60.dp).background(color = Color(0xFFFFFFFF))
                    ) { // 描画領域のサイズを指定
                        drawRect(color = Color.White, size = size, blendMode = BlendMode.SrcOver)

                        drawCircle(
                            color = Color(0xFFDFDFDF), // 円の色
                            radius = 30.toFloat(), // 円の半径、描画領域の半分のサイズ
                            center = center // 描画位置（中央）
                        )
                    }
                }
                Column(Modifier.fillMaxWidth()) {
                    Text(
                        text = "${beginTime}-${endTime}",
                        style = TextStyle(color = Color(0xFF8F9BB3), fontSize = 12.sp)
                    )
                    Spacer(Modifier.height(3.dp))
                    Text(
                        text = taskName,
                        style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp)
                    )
                    Spacer(Modifier.height(3.dp))
                    Text(
                        text = description,
                        style =
                        TextStyle(
                            color = Color(0xFF8F9BB3),
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        )
                    )
                }
            }
        }
    }
}
