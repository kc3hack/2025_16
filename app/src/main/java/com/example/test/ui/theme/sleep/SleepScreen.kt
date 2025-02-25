package com.example.test

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlinx.coroutines.delay

val inFFamily =
FontFamily(
        Font(R.font.inter_24pt_medium),
        Font(R.font.inter_24pt_extralight, FontWeight.ExtraLight)
)
// fontを追加する
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
    Row(modifier = Modifier.fillMaxWidth().padding(start = 10.dp, end = 10.dp)) {
        Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(0.dp)
        ) {
            Text(
                    text =
                            buildAnnotatedString {
                                pushStyle(
                                        SpanStyle(
                                                fontSize = 12.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = Color(0xFF171D1B)
                                        )
                                )
                                append("${name}/Level.${level}・")
                                pop()
                                pushStyle(SpanStyle(fontSize = 13.sp, color = Color(0xFF3F4946)))
                                append("あと${time}時間でレベルアップ!")
                                pop()
                                // 直前の `pushStyle` を解除

                            }
            )
            Spacer(modifier = Modifier.height(2.dp))
            LinearProgressIndicator(
                    progress = expBar,
                    color = Color(0xFF006B5F),
                    trackColor = Color(0xFFDAE5E1),
                    modifier = Modifier.height(4.dp).width(300.dp)
            )
        }
    }
}

@Composable
fun CurrentTime() {
    var currentTime by remember { mutableStateOf<String>(getCurrentTime()) }

    LaunchedEffect(Unit) {
        while (true) {
            currentTime = getCurrentTime()
            delay(1000L) // 1秒ごとに更新
        }
    }
    Box() {
        Text(
                text = currentTime,
                fontWeight = FontWeight.ExtraLight,
                style = TextStyle(fontSize = 96.sp),
                textAlign = TextAlign.Center,
                fontFamily = FontFamily(Font(R.font.inter_24pt_extralight)),
                // fontを追加する
                modifier = Modifier.padding(top = 15.dp)
        )
    }
}

fun getCurrentTime(): String {
    val sdf = SimpleDateFormat("HH:mm", Locale.getDefault())
    return sdf.format(Date())
}

@Composable
fun SleepTimer(onNavigateBack: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.height(20.dp))
        Text(
                text = "おはようございます",
                fontWeight = FontWeight.Bold,
                style =
                        TextStyle(
                                fontSize = 32.sp,
                        ),
                modifier = Modifier.padding(top = 25.dp)
        )
        CurrentTime()
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            Image(
                    painter = painterResource(id = R.drawable.sleepywhitecat),
                    // 画像を追加する
                    contentDescription = "My Image",
                    modifier =
                            Modifier
                                .fillMaxWidth() // 幅を画面いっぱいに
                                .aspectRatio(1f) // 高さを幅と同じにする（正方形）
                                    .offset(y = (-90).dp) // 画像の位置
            )

            Box(
                    modifier =
                            Modifier.size(width = 158.dp, height = 36.dp)
                                    .zIndex(1f)
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(color = Color(0xFFF9D981).copy(alpha = 0.6f)),
                    contentAlignment = Alignment.Center
            ) { Text(text = "ねむ", fontSize = 12.sp, modifier = Modifier.offset().zIndex(1f)) }
        }
        Box(
                modifier =
                        Modifier.size(width = 291.dp, height = 34.dp)
                                .offset(y = (-100).dp)
                                .clip(RectangleShape)
                                .background(color = Color(0xFF9B9999).copy(alpha = 0.37f))
        ) { LevelCounter(name = "Sleep", level = 1, time = 5, expBar = 0.5f) }
        Column(modifier = Modifier.fillMaxWidth().offset(y = -50.dp)) {
            Text(
                text = "現在の睡眠貯金",
                textAlign = TextAlign.Left,
                fontWeight = FontWeight.Bold,
                style = TextStyle(fontSize = 18.sp),
                modifier = Modifier.padding(start = 20.dp)
            )
            Text(
                text = "+03:00",
                fontWeight = FontWeight.ExtraLight,
                style = TextStyle(fontSize = 48.sp),
                color = Color(0xFF49DF0D),
                modifier = Modifier.align(Alignment.CenterHorizontally),
                fontFamily = FontFamily(Font(R.font.inter_24pt_extralight))
                // fontを追加する
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "睡眠貯金が2時間から3時間になりました",
                fontWeight = FontWeight.Bold,
                style = TextStyle(fontSize = 18.sp),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Text(
                text = "すばらしい、理想的な睡眠時間です",
                fontWeight = FontWeight.Bold,
                style = TextStyle(fontSize = 18.sp),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}