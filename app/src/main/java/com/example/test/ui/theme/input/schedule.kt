package com.example.test.ui.theme.input

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

@Composable
fun Schedule() {
    val name = remember { mutableStateOf("") }

    TextField(
            value = name.value,
            onValueChange = { name.value = it },
            label = { Text("入力してください") },
            placeholder = { Text("テキストを入力...") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(0.8f)
    )
}
