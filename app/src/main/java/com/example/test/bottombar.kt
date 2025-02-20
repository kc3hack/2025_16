package com.example.test

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.test.ui.theme.home.*

// @Composable
// fun WatchScreen(onSwitch: () -> Unit) {
//     Text(text = "this is second screen!")
// }

@Composable
fun CalendarScreen(onSwitch: () -> Unit) {
    Text(text = "this is third screen.")
}

@Composable
fun Cats(onSwitch: () -> Unit) {
    Text(text = "this is forth screen.")
}

enum class ScreenState {
    First,
    Second,
    Third,
    Forth
}

@Composable
fun ScreenSwitcher() {
    val screenState = remember { mutableStateOf(ScreenState.First) }
    Scaffold(
            modifier = Modifier.background(color = Color.Transparent),
            bottomBar = { BottomAppBarExample(screenState = screenState) },
            floatingActionButton = {
                FloatingActionButton(
                        onClick = { /*ここにタスク追加の処理 */},
                        containerColor = Color(0xFF735BF2),
                        elevation = FloatingActionButtonDefaults.elevation(12.dp),
                        modifier =
                                Modifier.offset(y = 60.dp)
                                        .clip(CircleShape)
                                        .background(color = Color.Transparent)
                ) {
                    Icon(
                            Icons.Filled.Add,
                            tint = Color.White,
                            contentDescription = "Localized description"
                    )
                }
            },
            floatingActionButtonPosition = FabPosition.Center,
            content = { innerPadding ->
                Box(modifier = Modifier.padding(innerPadding)) {
                    when (screenState.value) {
                        ScreenState.First ->
                                HomeScreen /*ページ名いれる。一番左のアイコンがFirst*/ {
                                    screenState.value = ScreenState.First
                                }
                        ScreenState.Second ->
                                SleepTimer /*ページ名*/ { screenState.value = ScreenState.Second }
                        ScreenState.Third ->
                                CalendarScreen /*ページ名*/ { screenState.value = ScreenState.Third }
                        ScreenState.Forth -> Cats /*ページ名*/ { screenState.value = ScreenState.Forth }
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
                IconButton(
                        onClick = { screenState.value = ScreenState.First },
                        modifier = Modifier.padding(start = 5.dp)
                ) { Icon(Icons.Filled.Home, contentDescription = "Localized description") }
                IconButton(
                        onClick = {
                            screenState.value = ScreenState.Second //
                        },
                        modifier = Modifier.offset(x = 10.dp)
                ) {
                    Icon(
                            Icons.Filled.DateRange,
                            contentDescription = "Localized description",
                    )
                }
                IconButton(
                        onClick = {
                            screenState.value = ScreenState.Third //
                        },
                        modifier = Modifier.offset(x = 150.dp)
                ) {
                    Icon(
                            Icons.Filled.Notifications,
                            contentDescription = "Localized description",
                    )
                }
                IconButton(
                        onClick = {
                            screenState.value = ScreenState.Forth //
                        },
                        modifier = Modifier.offset(x = 155.dp)
                ) {
                    Icon(
                            Icons.Filled.Person,
                            contentDescription = "Localized description",
                    )
                }
            }
    )
}
