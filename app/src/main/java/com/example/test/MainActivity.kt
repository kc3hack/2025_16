package com.example.test

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.test.ui.theme.TestTheme
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TestTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // Greeting(
                    //     name = "Android",
                    //     modifier = Modifier.padding(innerPadding)
                    // )
                    TalkCats(modifier=Modifier.padding(innerPadding))
                }


            }
        }
    }
}

// @Composable
// fun Greeting(name: String, modifier: Modifier = Modifier) {
//     Text(
//         text = "Hello $name!",
//         modifier = modifier
//     )
// }

@Composable
fun TalkCats(modifier:Modifier=Modifier){
    Row(modifier=modifier
        .fillMaxWidth()
        .padding(40.dp)
        ){
    Box(modifier
        .weight(1f)
        .background(color=Color(0x99F9D981),shape=RoundedCornerShape(topStart=8.dp,bottomEnd=8.dp,topEnd=8.dp,bottomStart=8.dp))
        .padding(24.dp),
        contentAlignment = Alignment.Center
        ){
            Text("ごはん")
        }
    Spacer(modifier = Modifier.width(50.dp))
    Box(modifier
        .weight(1f)
        .background(color=Color(0x99F9D981),shape=RoundedCornerShape(topStart=8.dp,bottomEnd=8.dp,topEnd=8.dp,bottomStart=8.dp))
        .padding(24.dp)
        ,contentAlignment = Alignment.Center
        ){
            Text("...。")
        }
        }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TestTheme {
        // Greeting("Android")
        TalkCats()
    }
}
