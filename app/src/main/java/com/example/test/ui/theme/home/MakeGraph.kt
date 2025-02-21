package com.example.test.ui.theme.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.test.ui.theme.home.*
import kotlin.math.abs

@Composable
fun MakeGraph(modifier: Modifier = Modifier, sleepTime: Float) {
        val maxValue = 10.toFloat()

        val (isEnough, graphNumber) =
                when {
                        sleepTime > 0 -> true to (sleepTime / maxValue)
                        sleepTime < 0 -> false to (abs(sleepTime) / maxValue)
                        else -> true to 0f
                }
        val sleepCount = String.format("%.1f", sleepTime)
        val boxWidth = (if (graphNumber > 1) 180 else (graphNumber * 180)).toInt()
        val boxOffset =
                (if (isEnough) ((LocalConfiguration.current.screenWidthDp.dp / 2))
                else ((LocalConfiguration.current.screenWidthDp.dp / 2) - boxWidth.dp))

        BoxWithConstraints {
                Box(
                        modifier =
                                Modifier.fillMaxWidth()
                                        .height(120.dp)
                                        .padding(top = 30.dp)
                                        .offset(x = boxOffset),
                ) {
                        Box(
                                modifier =
                                        Modifier.height(26.dp)
                                                .background(
                                                        if (isEnough) Color(0xA036C2E1)
                                                        else Color(0xFFFF842D)
                                                )
                                                .width(boxWidth.dp)
                        ) {
                                if (sleepTime >= (2.2).toFloat()) {
                                        Text(
                                                text = "${sleepCount}",
                                                style =
                                                        TextStyle(
                                                                color = Color(0xFFFFFFFF),
                                                                fontWeight = FontWeight.Light,
                                                                fontSize = 25.sp
                                                        ),
                                                modifier =
                                                        Modifier.align(Alignment.CenterEnd)
                                                                .then(
                                                                        if (isEnough)
                                                                                Modifier.align(
                                                                                                Alignment
                                                                                                        .CenterEnd
                                                                                        )
                                                                                        .padding(
                                                                                                // top =
                                                                                                // 1.dp,
                                                                                                // bottom =
                                                                                                //
                                                                                                // 1.dp,
                                                                                                end =
                                                                                                        5.dp
                                                                                        )
                                                                        else
                                                                                Modifier.align(
                                                                                                Alignment
                                                                                                        .CenterStart
                                                                                        )
                                                                                        .padding(
                                                                                                // top =
                                                                                                // 1.dp,
                                                                                                // bottom =
                                                                                                //
                                                                                                // 1.dp,
                                                                                                start =
                                                                                                        5.dp
                                                                                        )
                                                                )
                                        )
                                }
                        }
                }
                Box(
                        modifier =
                                Modifier.fillMaxWidth()
                                        .offset(
                                                x =
                                                        ((LocalConfiguration.current
                                                                .screenWidthDp
                                                                .dp / 2) - 1.dp),
                                                y = 17.dp
                                        )
                ) {
                        Box(
                                modifier =
                                        Modifier.width(2.dp)
                                                .height(60.dp)
                                                .background(color = Color(0xFFDAE5E1))
                                                .padding(top = 40.dp)
                        ) {}
                }
                Divider(
                        color = Color(0xFFDAE5E1),
                        thickness = 2.dp,
                        modifier = Modifier.padding(top = 73.dp, start = 25.dp, end = 25.dp)
                )

                Row(
                        modifier = Modifier.fillMaxWidth().padding(top = 75.dp),
                        horizontalArrangement = Arrangement.Center
                ) {
                        Text(
                                text = "-5",
                                style =
                                        TextStyle(
                                                color = Color(0xFFDAE5E1),
                                                fontWeight = FontWeight.Light,
                                                fontSize = 17.sp
                                        )
                        )
                        Spacer(modifier = Modifier.width(175.dp))
                        Text(
                                text = "5",
                                style =
                                        TextStyle(
                                                color = Color(0xFFDAE5E1),
                                                fontWeight = FontWeight.Light,
                                                fontSize = 17.sp
                                        )
                        )
                }
                Row(
                        modifier = Modifier.fillMaxWidth().padding(top = 80.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                        Text(
                                text = "不足",
                                style =
                                        TextStyle(
                                                color = Color(0xFFFF842D),
                                                fontWeight = FontWeight.Light,
                                                fontSize = 18.sp
                                        )
                        )
                        Spacer(Modifier.width(80.dp))
                        Text(
                                text = "睡眠状況",
                                style = TextStyle(fontWeight = FontWeight.Light, fontSize = 20.sp)
                        )
                        Spacer(Modifier.width(80.dp))
                        Text(
                                text = "順調",
                                style =
                                        TextStyle(
                                                color = Color(0xA036C2E1),
                                                fontWeight = FontWeight.Light,
                                                fontSize = 18.sp
                                        )
                        )
                }
        }
}
