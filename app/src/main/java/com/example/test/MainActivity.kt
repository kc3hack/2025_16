package com.example.test

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.test.ui.theme.TestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TestTheme {
                HomeScreen()
                Surface() { ScreenSwitcher() }
            }
        }
    }
}

@Composable fun HomeScreen() {}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TestTheme {
        // HomeScreen(navController)
    }
}
