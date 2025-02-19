package com.example.test

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.test.ui.theme.*
import com.example.test.ui.theme.home.*

class MainActivity : ComponentActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                enableEdgeToEdge()
                setContent {
                        TestTheme {
                                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                                        Column(
                                                modifier =
                                                        Modifier.fillMaxSize()
                                                                .padding(innerPadding),
                                                horizontalAlignment = Alignment.CenterHorizontally,
                                                verticalArrangement = Arrangement.spacedBy(-40.dp)
                                        ) {
                                                // TalkCatsのUI
                                                TalkCats(modifier = Modifier.padding(top = 5.dp))
                                                LevelList(modifier = Modifier) // LevelListのUI
                                                Spacer(modifier = Modifier.height(80.dp))
                                                SleepInfo(modifier = Modifier)
                                                ToDoList(modifier=Modifier)
                                        }
                                }
                        }
                }
        }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
        TestTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                        Column(
                                modifier = Modifier.fillMaxSize().padding(innerPadding),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(-40.dp)
                        ) {
                                // TalkCatsのUI
                                TalkCats(modifier = Modifier.padding(top = 5.dp))
                                LevelList(modifier = Modifier) // LevelListのUI
                                Spacer(modifier = Modifier.height(80.dp))
                                SleepInfo(modifier = Modifier)
                                ToDoList(modifier=Modifier)
                        }
                }
        }
}
