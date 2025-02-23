package com.example.test
import android.app.TimePickerDialog
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx. compose. material3.Text
import androidx. compose. material3.Button
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx. compose. foundation. background
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx. compose. ui. platform. LocalContext
import androidx. compose. ui. graphics. Color
import androidx.compose.material3.IconButton
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx. compose. foundation. rememberScrollState
import androidx. compose. foundation. verticalScroll
import java.util.*
import kotlin.coroutines.jvm.internal.*
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.BroadcastReceiver
import android.widget.Toast
import android. widget. NumberPicker
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx. compose. ui. tooling. preview. Preview


@Composable
fun NumberPicker(value: Int, onValueChange: (Int) -> Unit, range: IntRange) {
    var selectedValue by remember { mutableIntStateOf(value) }

    Row {
        IconButton(onClick = {
            if (selectedValue > range.first) {
                selectedValue--
                onValueChange(selectedValue)
            }
        }) {
            Text(text = "-", fontSize = 24.sp)
        }
        Text(text = selectedValue.toString(), modifier = Modifier.padding(16.dp))
        IconButton(onClick = {
            if (selectedValue < range.last) {
                selectedValue++
                onValueChange(selectedValue)
            }
        }) {
            Icon(Icons.Filled.Add, contentDescription = "Increase")
        }
    }
}


@Composable
private fun ScrollBoxes() {
    Column(
        modifier = Modifier
            .background(Color.LightGray)
            .size(100.dp)
            .verticalScroll(rememberScrollState())
    ) {
        repeat(10) {
            Text("Item $it", modifier = Modifier.padding(2.dp))
        }
    }
}






