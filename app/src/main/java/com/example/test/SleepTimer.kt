package com.example.test
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun SleepTimer(onNavigateBack: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "This is the second screen.")
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