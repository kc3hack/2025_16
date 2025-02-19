package com.example.test.ui.theme.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.test.ui.theme.home.*

@Composable
fun LevelList(modifier: Modifier = Modifier) {
        val workLevel = 1
        val workTime = 1
        val workExpBar = 0.4.toFloat()
        val sleepLevel = 1
        val sleepTime = 1
        val sleepExpBar = 0.5.toFloat()
        Box(
                modifier =
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
                                .padding(top = 2.dp, bottom = 8.dp)
        ) {
                Column(
                        modifier = Modifier.fillMaxWidth().padding(5.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(0.dp)
                ) {
                        LevelCounter(
                                modifier = Modifier.padding(0.dp),
                                "Work",
                                workLevel,
                                workTime,
                                workExpBar
                        )
                        LevelCounter(
                                modifier = Modifier.padding(0.dp),
                                "Sleep",
                                sleepLevel,
                                sleepTime,
                                sleepExpBar
                        )
                }
        }
}
