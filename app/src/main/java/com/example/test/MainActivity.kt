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
import com.example.test.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale

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
        .padding(end = 20.dp)
        .fillMaxWidth()
        ){
        Image(
                painter = painterResource(id = R.drawable.workcat),
                contentDescription = "working cat",
                modifier = Modifier
                    .size(80.dp),
                contentScale = ContentScale.Fit
            )
        Box(modifier
        .background(color=Color(0x99F9D981),shape=RoundedCornerShape(topStart=8.dp,bottomEnd=8.dp,topEnd=8.dp,bottomStart=8.dp))
        .padding(24.dp)
        .width(60.dp)
        .align(Alignment.Center),
        contentAlignment = Alignment.Center
        ){
            Text("ごはん")
        }
    }
    Spacer(modifier = Modifier.width(20.dp))
    Box(modifier
        .weight(1f)
        .padding(end = 20.dp)
        .fillMaxWidth()){
        Image(
                painter = painterResource(id = R.drawable.sleepcat),
                contentDescription = "sleeping cat",
                modifier = Modifier
                    .size(80.dp),
                contentScale = ContentScale.Fit
            )
        Box(modifier
        .background(color=Color(0x99F9D981),shape=RoundedCornerShape(topStart=8.dp,bottomEnd=8.dp,topEnd=8.dp,bottomStart=8.dp))
        .padding(24.dp)
        .width(60.dp)
        .align(Alignment.Center)
        ,contentAlignment = Alignment.Center
        ){
            
            Text("...。")
        }
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
