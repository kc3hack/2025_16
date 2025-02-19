package com.example.test
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.filled.Edit
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
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
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.graphics.PathFillType
import android.graphics.drawable.shapes.Shape
import android.util.Size
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlinx.coroutines.delay
import java.util.Calendar
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.platform.LocalDensity


@Composable
fun onSave() {
    Box(){
    Text(text = "セーブが押されました")}}
@Composable
fun onCancel() {
    Box(){Text(text = "キャンセルが押されました")}}

@Composable
public fun AlarmScreen(onSwitch: () -> Unit) {
    var buttonClicked by remember { mutableStateOf(false) }
    Column(modifier = Modifier.fillMaxSize()
        .background(color = Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.height(80.dp))
        Box(
            modifier = Modifier
                .size(width = 343.dp, height = 343.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(color = Color(0xFF252B26))
                .align(alignment = Alignment.CenterHorizontally)
        ) {
            Text(
                text = "アラームをセット",
                fontFamily = FontFamily(Font(R.font.inter_24pt_semibold)),
                fontSize = 20.sp,
                color = Color.White,
                modifier = Modifier.padding(top = 18.dp, start = 15.dp)
            )

            Button(
                onClick = { buttonClicked = true }/*TODO*/, modifier = Modifier
                    .size(width = 148.dp, height = 56.dp)
                    .offset(x = 15.dp, y = 270.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(color = 0xFF343835))
                    .border(1.dp,Color(color = 0xFF4E4E4E))

            ) {
                Text(
                    text = "Cancel",
                    color = Color.White,
                    fontSize = 16.sp
                )
            }

            Button(
                onClick = { buttonClicked = true }/*TODO*/,
                modifier = Modifier
                    .size(width = 148.dp, height = 56.dp)
                    .offset(x = 180.dp, y = 270.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(color = 0xFF349053))

            ) {
                Text(
                    text = "Save",
                    color = Color.White,
                    fontSize = 16.sp
                )

            }
            if (buttonClicked) {
                Text("ボタン押されたよ")
            }
        }

    }

        Text(
            text = "睡眠口座",
            fontSize = 20.sp,
            color = Color.White,
            fontFamily = FontFamily(Font(R.font.inter_24pt_semibold)),
            modifier = Modifier.padding(start = 20.dp)
                .offset(y = 455.dp)
        )
    Column(modifier = Modifier.fillMaxWidth()
        .offset(y = 490.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    )

    {
        Text(
            text = "+02:00",
            textAlign = TextAlign.Center,
            color = Color(0xFF8AFF5C),
            fontSize = 96.sp,
            fontFamily = FontFamily(Font(R.font.inter_24pt_extralight)),
        )

        Text(
            text = "とてもよく眠れています！",
            color = Color.White,
            fontSize = 20.sp,
            fontFamily = FontFamily(Font(R.font.inter_24pt_semibold)),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 20.dp)
        )
    }




}


@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
fun AlarmScreenPreview() {
    AlarmScreen(
        onSwitch = {}
    )
}

