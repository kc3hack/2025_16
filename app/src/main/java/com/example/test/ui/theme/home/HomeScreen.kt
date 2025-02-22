package com.example.test.ui.theme.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.test.ui.theme.ToDoList

@Composable
fun HomeScreen(onNavigateBack: () -> Unit) {
    Scaffold(modifier = Modifier.fillMaxSize().background(color = Color.White)) { innerPadding ->
        Column(
                modifier =
                        Modifier.fillMaxSize()
                                .background(color = Color.White)
                                .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(-40.dp)
        ) {
            // TalkCatsのUI
            TalkCats(modifier = Modifier.padding(top = 15.dp))
            LevelList(modifier = Modifier) // LevelListのUI
            Spacer(modifier = Modifier.height(80.dp))
            SleepInfo(modifier = Modifier)
            ToDoList(modifier = Modifier)
        }
    }
}
