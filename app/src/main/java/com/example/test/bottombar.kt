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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.example.test.ui.theme.calendar.*
import com.example.test.ui.theme.home.*
import com.example.test.ui.theme.input.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import androidx.lifecycle.viewmodel.compose.viewModel
// @Composable
// fun WatchScreen(onSwitch: () -> Unit) {
//     Text(text = "this is second screen!")
// }

// @Composable
// fun CalendarScreen(onSwitch: () -> Unit) {
//    Text(text = "this is third screen.")
// }

@Composable
fun Cats(onSwitch: () -> Unit) {
    Text(text = "this is forth screen.")
}

 enum class ScreenState {
     First,
     Second,
     Third,
     Forth,
     Fifth
 }
class ScreenViewModel : ViewModel() {
    private val _screenState = MutableStateFlow(ScreenState.First)
    val screenState: StateFlow<ScreenState> = _screenState

    fun navigateTo(screen: ScreenState) {
        _screenState.value = screen
    }
}

@Composable
fun ScreenSwitcher(viewModel: ScreenViewModel= androidx.lifecycle.viewmodel.compose.viewModel()) {
    val screenState by viewModel.screenState.collectAsState()

    Scaffold(
            modifier = Modifier.background(color = Color.Transparent),
            bottomBar = { BottomAppBarExample(viewModel) },
            floatingActionButton = {
                FloatingActionButton(
                        onClick = { viewModel.navigateTo(ScreenState.Fifth) },
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
                    when (screenState) {
                        ScreenState.First ->
                                HomeScreen /*ページ名いれる。一番左のアイコンがFirst*/ {
                                    viewModel.navigateTo(ScreenState.First)
                                }
                        ScreenState.Second ->
                                CalenderScreen /*ページ名*/ { viewModel.navigateTo(ScreenState.Second) }
                        ScreenState.Third ->
                                SleepTimer /*ページ名*/ { viewModel.navigateTo(ScreenState.Third) }
                        ScreenState.Forth ->
                                Cats /*ページ名*/ { viewModel.navigateTo(ScreenState.Forth) }
                        ScreenState.Fifth ->
                                InputScreen /*ページ名*/ { viewModel.navigateTo(ScreenState.Fifth) }
                    }
                }
            }
    )
}

@Composable
fun BottomAppBarExample(viewModel: ScreenViewModel) {
    BottomAppBar(
            modifier = Modifier.height(76.dp),
            actions = {
                IconButton(
                        onClick = { viewModel.navigateTo(ScreenState.First) },
                        modifier = Modifier.padding(start = 13.dp)
                ) { Icon(Icons.Filled.Home, contentDescription = "Localized description") }
                IconButton(
                        onClick = {
                            viewModel.navigateTo(ScreenState.Second)
                            //
                        },
                        modifier = Modifier.offset(x = 19.dp)
                ) {
                    Icon(
                            Icons.Filled.DateRange,
                            contentDescription = "Localized description",
                    )
                }
                IconButton(
                        onClick = {
                            viewModel.navigateTo(ScreenState.Third)
                            //
                        },
                        modifier = Modifier.offset(x = 160.dp)
                ) {
                    Icon(
                            Icons.Filled.Notifications,
                            contentDescription = "Localized description",
                    )
                }
                IconButton(
                        onClick = {
                            viewModel.navigateTo(ScreenState.Forth)
                            //
                        },
                        modifier = Modifier.offset(x = 180.dp)
                ) {
                    Icon(
                            Icons.Filled.Person,
                            contentDescription = "Localized description",
                    )
                }
            }
    )
}
