package com.example.test.ui.theme.input

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun InputScreen(onNavigateBack: () -> Unit) {
    val count = remember { mutableIntStateOf(0) }
    Box(
            Modifier.fillMaxWidth() // 画面全体を埋める
                    .padding(16.dp), // 適宜余白を調整
            contentAlignment = Alignment.Center // 中央寄せ
    ) {
        Box(
                Modifier.fillMaxWidth(0.98f).background(color = Color(0xFFFCEF8F)),
                contentAlignment = Alignment.Center
        ) {
            Row(
                    modifier = Modifier.fillMaxWidth().padding(top=10.dp,bottom=10.dp),
                    horizontalArrangement = Arrangement.Center
            ) {
                Box(Modifier.clickable { count.intValue = 0 }) {
                    Text(text = "固定の予定", style = TextStyle(fontSize = 13.sp))
                }
                Spacer(Modifier.width(10.dp))
                Box(Modifier.clickable { count.intValue = 1 }) {
                    Text(text = "課題(期限あり)", style = TextStyle(fontSize = 13.sp))
                }
                Spacer(Modifier.width(10.dp))

                Box(Modifier.clickable { count.intValue = 2 }) {
                    Text(text = "課題(期限なし)", style = TextStyle(fontSize = 13.sp))
                }
            }
        }
    }
}
