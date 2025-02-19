package com.example.test
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.FabPosition
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.IconButton
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.fontResource
import androidx.compose.ui.zIndex
import androidx. compose. ui. unit. LayoutDirection
import androidx. compose. ui. graphics. PathFillType
import android. graphics. drawable. shapes. Shape
import android. util. Size
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlinx.coroutines.delay
import java.util.Calendar
import kotlin. io. path. Path
import kotlin.io.path.moveTo
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.platform.LocalDensity



public val inFFamily = FontFamily(
    Font(R.font.inter_24pt_medium),
    Font(R.font.inter_24pt_extralight,FontWeight.ExtraLight),
    Font(R.font.inter_24pt_semibold,FontWeight.SemiBold)
)


@Composable
fun LevelCounter(modifier:Modifier = Modifier,name:String,level:Int,time:Int,expBar:Float){
    val imageId = when (name){
        "Work" -> R.drawable.workcat
        "Sleep" -> R.drawable.sleepcat
        else -> {R.drawable.workcat}
    }
    Row(modifier=Modifier
        .fillMaxWidth()
        .padding(start = 10.dp, end = 10.dp)
    ){
        Column(modifier = Modifier
            .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(0.dp)
        ){
            Text(
                text = buildAnnotatedString {
                    pushStyle(SpanStyle(fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color(0xFF171D1B)))
                    append("${name}/Level.${level}・")
                    pop()
                    pushStyle(SpanStyle(fontSize = 13.sp, color = Color(0xFF3F4946)))
                    append("あと${time}時間でレベルアップ!")
                    pop()
                    // 直前の `pushStyle` を解除

                })
            Spacer(modifier = Modifier.height(2.dp))
            LinearProgressIndicator(progress = expBar,color=Color(0xFF006B5F),trackColor=Color(0xFFDAE5E1),
                modifier=Modifier
                    .height(4.dp)
                    .width(300.dp)
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
            fontFamily = FontFamily(Font(R.font.inter_24pt_extralight )),
            modifier = Modifier.padding(top=15.dp)
        )
    }
}
fun getCurrentTime(): String {
    val sdf = SimpleDateFormat("HH:mm", Locale.getDefault())
    return sdf.format(Date())}
@Composable
fun ScreenSwitcher() {
    val screenState = remember { mutableStateOf(ScreenState.First) }
    Scaffold (
        bottomBar = {
            BottomAppBarExample(screenState = screenState)
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { screenState.value = ScreenState.Second },
                containerColor = Color(0xFF735BF2),
                elevation = FloatingActionButtonDefaults.elevation(12.dp),
                modifier = Modifier
                    .offset(y = 60.dp)
                    .clip(CircleShape)
            ) {
                Icon(Icons.Filled.Add,
                    tint = Color.White,
                    contentDescription = "Localized description")
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        content = { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                when (screenState.value) {
                    ScreenState.First -> SleepTimer /*ページ名いれる。一番左のアイコンがFirst*/ { screenState.value = ScreenState.First }
                    ScreenState.Second -> WatchScreen /*ページ名*/  { screenState.value = ScreenState.Second }
                    ScreenState.Third -> AlarmScreen  /*ページ名*/  { screenState.value = ScreenState.Third }
                    ScreenState.Forth -> Cats /*ページ名*/  { screenState.value = ScreenState.Forth}
                }
            }
        }
    )
}

@Composable
fun BottomAppBarExample(screenState: MutableState<ScreenState>) {
    BottomAppBar(
        modifier = Modifier.height(76.dp),
        actions = {
            IconButton(onClick = { screenState.value = ScreenState.First },
            modifier=Modifier
                .padding(start = 5.dp)) {
                Icon(
                    Icons.Filled.Home,
                    contentDescription = "Localized description"
                )
            }
            IconButton(onClick = { screenState.value = ScreenState.Second //
            },
            modifier=Modifier
                .offset(x = 10.dp)
                ) {
                Icon(
                    Icons.Filled.DateRange,
                    contentDescription = "Localized description",
                    )
            }
            IconButton(onClick = { screenState.value = ScreenState.Third //
            },
                modifier=Modifier
                    .offset(x = 150.dp)) {
                Icon(
                    Icons.Filled.Notifications,
                    contentDescription = "Localized description",
                    )
            }
            IconButton(onClick = { screenState.value = ScreenState.Forth //
            },
            modifier = Modifier
                .offset(x = 155.dp)) {
                Icon(
                    Icons.Filled.Person,
                    contentDescription = "Localized description",
                    )
            }
                  }
        )
}



@Composable
fun SleepTimer(onNavigateBack: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "おはようございます", fontWeight = FontWeight.Bold,
            style = TextStyle(fontSize = 32.sp,),
            modifier = Modifier.padding(top = 25.dp)
        )
        CurrentTime()
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.sleepywhitecat),
                contentDescription = "My Image",
                modifier = Modifier
                    .size(width = 390.dp, height = 390.dp)
                    .offset(y = (-90).dp) //画像の位置

            )

            Box(
                modifier = Modifier
                    .size(width = 158.dp, height = 36.dp)
                    .offset(x = 100.dp, y = 220.dp)
                    .zIndex(1f)
                    .clip(RoundedCornerShape(8.dp))
                    .background(color = Color(0xFFF9D981).copy(alpha = 0.6f)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "ねむ",
                    fontSize = 12.sp,
                    modifier = Modifier
                        .offset()
                        .zIndex(1f)
                )
            }
        }
        Box(
            modifier = Modifier
                .size(width = 291.dp, height = 34.dp)
                .offset(y = (-110).dp)
                .clip(RectangleShape)
                .background(color = Color(0xFF9B9999).copy(alpha = 0.37f))
        ) {
            LevelCounter(name = "Sleep", level = 1, time = 5, expBar = 0.5f)
        }
}
        Column(modifier = Modifier
            .fillMaxWidth()
            .offset(y = 535.dp)
        ) {
            Text(
                text = "現在の睡眠貯金",
                textAlign = TextAlign.Left,
                fontWeight = FontWeight.Bold,
                style = TextStyle(fontSize = 18.sp),
                modifier = Modifier
                    .padding(start = 20.dp)
            )
            Text(
                text = "+03:00",
                fontWeight = FontWeight.ExtraLight,
                style = TextStyle(fontSize = 48.sp),
                color = Color(0xFF49DF0D),
                modifier = Modifier.align(Alignment.CenterHorizontally),
                fontFamily = FontFamily(Font(R.font.inter_24pt_extralight))

            )
            Text(
                text = "睡眠貯金が2時間から3時間になりました", fontWeight = FontWeight.Bold,
                style = TextStyle(fontSize = 18.sp),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Text(
                text = "すばらしい、理想的な睡眠時間です", fontWeight = FontWeight.Bold,
                style = TextStyle(fontSize = 18.sp),
                modifier = Modifier.align(Alignment.CenterHorizontally)

            )
            Button(onClick = onNavigateBack) {
                Text(text = "Go back")
            }
        }

}

@Composable
fun WatchScreen (onSwitch: () -> Unit){
    Text(text="this is second screen!" )
}

@Composable
fun CalendarScreen(onSwitch: () -> Unit){
    Text(text = "this is third screen.")
}

@Composable
fun Cats(onSwitch: () -> Unit){
    Text(text = "this is forth screen.")
}

enum class ScreenState {
    First, Second, Third,Forth
}


@Preview(showBackground = true)
@Composable
fun SecondScreenPreview(){SleepTimer(onNavigateBack = {})
    val screenState = remember { mutableStateOf(ScreenState.First) }
    ScreenSwitcher()
}
@Preview(showBackground = true)
@Composable
fun AlarmPreview() { AlarmScreen (onSwitch = {})
    val screenState = remember { mutableStateOf(ScreenState.Third) }

}
@Preview(showBackground = true)
@Composable
fun CatsScreenPreview() {
    val screenState = remember { mutableStateOf(ScreenState.Forth) }

}