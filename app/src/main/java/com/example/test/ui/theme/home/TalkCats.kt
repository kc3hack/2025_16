package com.example.test.ui.theme.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.test.R
import com.example.test.ui.theme.home.*
import com.example.test.utils.Controller

@Composable
fun TalkCats(modifier: Modifier = Modifier) {
        var workCatLevel = Controller.catLevels.getWorkCatLevel()
        var sleepCatLevel = Controller.catLevels.getSleepCatLevel()
        var workText = makeCatText(workCatLevel, "work")
        var sleepText = makeCatText(sleepCatLevel, "sleep")

        Row(
                modifier =
                        modifier.fillMaxWidth()
                                .padding(top = 10.dp, start = 10.dp, end = 10.dp)
                                .height(190.dp)
        ) {
                Box(modifier.weight(1f).fillMaxWidth()) {
                        Image(
                                painter =
                                        (if (workCatLevel == 0||workCatLevel == 1)
                                                painterResource(id = R.drawable.workcat)
                                        else if (workCatLevel >= 2)
                                                painterResource(id = R.drawable.workcat2)
                                        else painterResource(id = R.drawable.workcat)),
                                contentDescription = "working cat",
                                modifier = Modifier.offset(y = (-70).dp).size(200.dp),
                                contentScale = ContentScale.Fit
                        )
                        Box(
                                modifier.background(
                                                color = Color(0x99F9D981),
                                                shape =
                                                        RoundedCornerShape(
                                                                topStart = 8.dp,
                                                                bottomEnd = 8.dp,
                                                                topEnd = 8.dp,
                                                                bottomStart = 8.dp
                                                        )
                                        )
                                        .padding(24.dp)
                                        .width(90.dp)
                                        .height(20.dp)
                                        .align(Alignment.Center),
                                contentAlignment = Alignment.Center
                        ) { Text(text = workText, style = TextStyle(fontSize = 10.sp)) }
                }
                Spacer(modifier = Modifier.width(20.dp).height(50.dp))
                Box(modifier.weight(1f).fillMaxWidth()) {
                        Image(
                                painter =
                                        (if (sleepCatLevel == 1||sleepCatLevel == 0)
                                                painterResource(id = R.drawable.sleepcat)
                                        else if (sleepCatLevel >= 2)
                                                painterResource(id = R.drawable.sleepcat2)
                                        else painterResource(id = R.drawable.workcat)),
                                contentDescription = "sleeping cat",
                                modifier = Modifier.offset(y = (-70).dp).size(200.dp),
                                contentScale = ContentScale.Fit
                        )
                        Box(
                                modifier.background(
                                                color = Color(0x99F9D981),
                                                shape =
                                                        RoundedCornerShape(
                                                                topStart = 8.dp,
                                                                bottomEnd = 8.dp,
                                                                topEnd = 8.dp,
                                                                bottomStart = 8.dp
                                                        )
                                        )
                                        .padding(24.dp)
                                        .width(90.dp)
                                        .height(20.dp)
                                        .align(Alignment.Center),
                                contentAlignment = Alignment.Center
                        ) { Text(text = sleepText, style = TextStyle(fontSize = 10.sp)) }
                }
        }
}
