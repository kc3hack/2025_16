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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx. compose. material3.TopAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.IconButton
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.LinearProgressIndicator
import androidx. compose.material3.FloatingActionButton
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
import com.example.test.R
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlinx.coroutines.delay
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx. compose. ui. platform. LocalDensity
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.graphics.drawscope.DrawScope


@Composable
fun MyApp() {
    Scaffold(
        bottomBar = {
            CustomBottomBar()
        }
    ) {
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(it)) {
            Text(text = "hello")// メインコンテンツをここに配置
        }
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            FloatingActionButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .offset(y = (-20).dp) // 必要に応じて調整
                    .clip(CircleShape)
                    .background(Color.White) // 背景色を指定
            ) {
                Text("FAB")
            }
        }
    }
}

@Composable
fun CustomBottomBar() {
    val density = LocalDensity.current
    val cutoutRadius = with(density){ 36.dp.toPx() }
    val height = with(density){ 56.dp.toPx() }
    val cutoutTop = height - (cutoutRadius * 2)
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(Color.Gray)
            .clip(BarShape),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "わーい"
        )// BottomAppBarのコンテンツをここに追加
    }
}


val BarShape = object : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: androidx.compose.ui.unit.Density
    ): Outline {
        val cutoutRadius = with(density){ 36.dp.toPx() }
        val cutoutTop = size.height - cutoutRadius * 2

        val path = Path().apply {
            moveTo(0f, size.height)
            lineTo(size.width / 2 - cutoutRadius, size.height)
            arcTo(
                Rect(
                    left = size.width / 2 - cutoutRadius,
                    top = cutoutTop,
                    right = size.width / 2 + cutoutRadius,
                    bottom = size.height
                ),
                180f,
                -180f,
                false
            )
            lineTo(size.width, size.height)
            lineTo(size.width, 0f)
            lineTo(0f, 0f)
            close()
            fillType = PathFillType.EvenOdd
        }

        return Outline.Generic(path)
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
MyApp()
    CustomBottomBar()


}



