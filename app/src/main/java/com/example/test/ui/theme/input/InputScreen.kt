package com.example.test.ui.theme.input

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.*
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
                Column(Modifier.fillMaxWidth()) {
                        Box(
                                Modifier.fillMaxWidth(0.98f)
                                        .background(color = Color(0xFFFCEF8F))
                                        .height(40.dp),
                                contentAlignment = Alignment.Center
                        ) {
                                Column(Modifier.fillMaxWidth()) {
                                        Row(
                                                modifier =
                                                        Modifier.fillMaxWidth()
                                                                .padding(
                                                                        top = 10.dp,
                                                                        bottom = 10.dp
                                                                ),
                                                horizontalArrangement = Arrangement.SpaceEvenly
                                        ) {
//                                                Spacer(Modifier.width(10.dp))

                                                Box(Modifier.clickable { count.intValue = 0 }) {
                                                        Text(
                                                                text = "固定の予定",
                                                                style = TextStyle(fontSize = 13.sp)
                                                        )
                                                }
                                                // Spacer(Modifier.width(10.dp))
                                                Box(Modifier.clickable { count.intValue = 1 }) {
                                                        Text(
                                                                text = "課題(期限あり)",
                                                                style = TextStyle(fontSize = 13.sp)
                                                        )
                                                }
                                                // Spacer(Modifier.width(15.dp))

                                                Box(Modifier.clickable { count.intValue = 2 }) {
                                                        Text(
                                                                text = "課題(期限なし)",
                                                                style = TextStyle(fontSize = 13.sp)
                                                        )
                                                }
                                        }
                                        // Schedule()
                                }
                                Divider(
                                        thickness = 5.dp,
                                        modifier =
                                                Modifier.padding(
                                                                top = 31.dp,
                                                                start = 140.dp,
                                                                end = 160.dp
                                                        )
                                                        .then(
                                                                if (count.intValue == 0)
                                                                        Modifier.offset(x = -105.dp)
                                                                else if (count.intValue == 2)
                                                                        Modifier.offset(x = 115.dp)
                                                                else Modifier
                                                        ),
                                        color = Color(0xFF65558F)
                                )

                                Divider(
                                        thickness = 1.dp,
                                        modifier =
                                                Modifier.padding(
                                                        top = 33.dp,
                                                        start = 8.dp,
                                                        end = 8.dp
                                                ),
                                        color = Color(0xFF65558F)
                                )
                        }
                        Box(
                                Modifier.fillMaxWidth(0.98f)
                                        .background(color = Color(0xFFFCEF8F))
                                        .padding(top = 50.dp),
                                contentAlignment = Alignment.Center
                        ) {
                                if (count.value == 0) {
                                        Schedule()
                                } else if (count.value == 1) {
                                        LimitedTask()
                                } else if (count.value == 2) {
                                        NoLimitedTask()
                                }
                        }
                }
        }
}
