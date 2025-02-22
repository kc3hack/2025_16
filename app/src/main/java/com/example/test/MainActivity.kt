package com.example.test

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.test.ui.theme.*
import com.example.test.ui.theme.home.*
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.test.*
class MainActivity : ComponentActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                enableEdgeToEdge()
                setContent {
                        val viewModel: ScreenViewModel = viewModel()
                        TestTheme {
                                HomeScreen(onNavigateBack = { finish() })
                                Surface() {
                                        ScreenSwitcher(viewModel)}
                        }
                }
        }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
        val context = LocalContext.current
        TestTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                        Column(
                                modifier = Modifier.fillMaxSize().padding(innerPadding),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(-40.dp)
                        ) {
                                // TalkCatsのUI
                                TalkCats(modifier = Modifier.padding(top = 5.dp))
                                LevelList(modifier = Modifier, context = context) // LevelListのUI
                                Spacer(modifier = Modifier.height(80.dp))
                                SleepInfo(modifier = Modifier)
                                ToDoList(modifier = Modifier)
                        }
                }
        }
}
