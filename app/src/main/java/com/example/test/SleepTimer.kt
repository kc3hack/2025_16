package com.example.test
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight

@Composable
fun SleepTimer(onNavigateBack: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "This is the second screen.")
        Text(text = "おはようございます",fontWeight = FontWeight.Bold,
            style = TextStyle(fontSize  = 32.sp,)
        )
        Text(text = "02:00",
            fontWeight = FontWeight.ExtraLight,
            style = TextStyle(fontSize = 96.sp),
            textAlign = TextAlign.Center)
        Text(text = "現在の睡眠貯金", fontWeight = FontWeight.Bold,
            style = TextStyle(fontSize = 20.sp),
            textAlign = TextAlign.Left)
        Text(text = "+03:00",
            fontWeight = FontWeight.ExtraLight,
            style = TextStyle(fontSize = 48.sp,
                color = Color(0xFF49DF0D))
        )
        Text(text = "睡眠貯金が2時間から3時間になりました",fontWeight = FontWeight.Bold,
            style = TextStyle(fontSize = 20.sp))
        Text(text = "すばらしい、理想的な睡眠時間です",fontWeight = FontWeight.Bold,
            style = TextStyle(fontSize = 20.sp))


        Button(onClick = onNavigateBack) {
            Text(text = "Go back")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SecondScreenPreview() {
    SleepTimer(onNavigateBack = {})
}