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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ToDoSleep(
        modifier: Modifier = Modifier,
        beginTime: String,
        endTime: String,
) {
    Box(Modifier.fillMaxWidth(0.97f).clip(
        RoundedCornerShape(10.dp)
    ).background(color = Color(0x80C0F6FF)).padding(vertical = 10.dp)) {
        Row(Modifier.padding(top = 0.dp)) {
            FloatingActionButton(
                    onClick = { println("clicked!") },
                    elevation = FloatingActionButtonDefaults.elevation(0.dp),
                    modifier = Modifier.background(color = Color.Transparent),
                    containerColor = Color.Transparent
            ) {
                Canvas(modifier = Modifier.size(60.dp)) { // 描画領域のサイズを指定
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
                        text = "Sleep with cats",
                        style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp)
                )
                Spacer(Modifier.height(3.dp))
                Text(
                        text = "ねこたちと今日はもう寝よう",
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
