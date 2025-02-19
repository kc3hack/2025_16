package com.example.test.ui.theme.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.test.R
import com.example.test.ui.theme.home.*

@Composable
fun LevelCounter(
        modifier: Modifier = Modifier,
        name: String,
        level: Int,
        time: Int,
        expBar: Float
) {
        val imageId =
                when (name) {
                        "Work" -> R.drawable.workcat
                        "Sleep" -> R.drawable.sleepcat
                        else -> {
                                R.drawable.workcat
                        }
                }
        Row(modifier = modifier.fillMaxWidth().padding(top = 10.dp, start = 10.dp, end = 10.dp)) {
                Image(
                        painter = painterResource(id = imageId),
                        contentDescription = "$name cat",
                        modifier = Modifier.size(35.dp).offset(x = (-7).dp, y = 5.dp),
                        contentScale = ContentScale.Fit
                )
                Column(
                        modifier = Modifier.fillMaxWidth().padding(2.dp),
                        verticalArrangement = Arrangement.spacedBy(0.dp)
                ) {
                        Text(
                                text =
                                        buildAnnotatedString {
                                                pushStyle(
                                                        SpanStyle(
                                                                fontSize = 17.sp,
                                                                fontWeight = FontWeight.Bold,
                                                                color = Color(0xFF171D1B)
                                                        )
                                                )
                                                append("${name}/Level.${level}・")
                                                pop()
                                                pushStyle(
                                                        SpanStyle(
                                                                fontSize = 13.sp,
                                                                color = Color(0xFF3F4946)
                                                        )
                                                )
                                                append("あと${time}時間でレベルアップ!")
                                                // 直前の `pushStyle` を解除

                                        }
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        LinearProgressIndicator(
                                progress = expBar,
                                color = Color(0xFF006B5F),
                                trackColor = Color(0xFFDAE5E1),
                                modifier = Modifier.height(4.dp).width(300.dp)
                        )
                }
        }
}
