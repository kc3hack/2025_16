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
import androidx.compose.foundation.layout.Column
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
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.material3.LinearProgressIndicator

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
                    // TalkCats(modifier=Modifier.padding(innerPadding))
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(-40.dp)
                    ) {
                        // TalkCatsのUI
                        TalkCats(modifier=Modifier.padding(top = 5.dp))
                        LevelList(modifier = Modifier) // LevelListのUI
                    }
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
        .padding(top=10.dp,start=10.dp,end=10.dp)
        .height(190.dp)
        ){
    Box(modifier
        .weight(1f)
        .fillMaxWidth()
        ){
        Image(
                painter = painterResource(id = R.drawable.workcat),
                contentDescription = "working cat",
                modifier = Modifier
                    .offset(y=(-70).dp)
                    .size(200.dp),
                contentScale = ContentScale.Fit
            )
        Box(modifier
        .background(color=Color(0x99F9D981),shape=RoundedCornerShape(topStart=8.dp,bottomEnd=8.dp,topEnd=8.dp,bottomStart=8.dp))
        .padding(24.dp)
        .width(90.dp)
        .height(20.dp)
        .align(Alignment.Center),
        contentAlignment = Alignment.Center
        ){
            Text("ごはん")
        }
    }
    Spacer(modifier = Modifier.width(20.dp).height(50.dp))
    Box(modifier
        .weight(1f)
        .fillMaxWidth()){
        Image(
                painter = painterResource(id = R.drawable.sleepcat),
                contentDescription = "sleeping cat",
                modifier = Modifier
                    .offset(y=(-70).dp)
                    .size(200.dp),
                contentScale = ContentScale.Fit
            )
        Box(modifier
        .background(color=Color(0x99F9D981),shape=RoundedCornerShape(topStart=8.dp,bottomEnd=8.dp,topEnd=8.dp,bottomStart=8.dp))
        .padding(24.dp)
        .width(90.dp)
        .height(20.dp)
        .align(Alignment.Center)
        ,contentAlignment = Alignment.Center
        ){
            
            Text("・・・。")
        }
    }
    
        }
}

@Composable
fun LevelList(modifier:Modifier=Modifier){
    val workLevel = 1
    val workTime = 1
    val workExpBar = 0.4.toFloat()
    val sleepLevel = 1
    val sleepTime = 1
    val sleepExpBar = 0.5.toFloat()
    Box(modifier=Modifier
            .background(color=Color(0x99F9D981),shape=RoundedCornerShape(topStart=8.dp,bottomEnd=8.dp,topEnd=8.dp,bottomStart=8.dp))
            .fillMaxWidth(0.97f)
            .padding(top = 2.dp,bottom = 8.dp)
        ){
            Column(modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(0.dp)){
                LevelCounter(modifier=Modifier.padding(0.dp), "Work",workLevel,workTime,workExpBar)
                LevelCounter(modifier=Modifier.padding(0.dp),"Sleep",sleepLevel,sleepTime,sleepExpBar)
            }
        }

}

@Composable
fun LevelCounter(modifier:Modifier = Modifier,name:String,level:Int,time:Int,expBar:Float){
    val imageId = when (name){
        "Work" -> R.drawable.workcat
        "Sleep" -> R.drawable.sleepcat
        else -> {R.drawable.workcat}
    }
    Row(modifier=modifier
        .fillMaxWidth()
        .padding(top=10.dp,start=10.dp,end=10.dp)){
            Image(
                painter = painterResource(id = imageId),
                contentDescription = "$name cat",
                modifier = Modifier
                   .size(35.dp)
                   .offset(x=(-7).dp,y=5.dp),
                contentScale = ContentScale.Fit
            ) 
            Column(modifier = Modifier
                        .fillMaxWidth()
                        .padding(2.dp),
                        verticalArrangement = Arrangement.spacedBy(0.dp)){
                            Text(
                            text = buildAnnotatedString {
                            pushStyle(SpanStyle(fontSize = 17.sp, fontWeight = FontWeight.Bold, color = Color(0xFF171D1B)))
                            append("${name}/Level.${level}・")
                            pop()
                            pushStyle(SpanStyle(fontSize = 13.sp, color = Color(0xFF3F4946)))
                            append("あと${time}時間でレベルアップ!")
                             // 直前の `pushStyle` を解除
                            
        })
            Spacer(modifier = Modifier.height(10.dp))
            LinearProgressIndicator(progress = expBar,color=Color(0xFF006B5F),trackColor=Color(0xFFDAE5E1),modifier=Modifier.height(4.dp).width(300.dp))
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
