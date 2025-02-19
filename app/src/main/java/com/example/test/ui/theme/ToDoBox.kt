package com.example.test.ui.theme

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ToDoBox(modifier: Modifier = Modifier) {
    Box(Modifier.fillMaxWidth().background(Color.White)) {
        Row(Modifier) {
            FloatingActionButton(
                    onClick = { println("clicked!") },
                    elevation = FloatingActionButtonDefaults.elevation(0.dp),
                    modifier = Modifier.background(color = Color.White)
            ) {
                Canvas(
                        modifier = Modifier.size(200.dp).background(color = Color(0xFFFFFFFF))
                ) { // 描画領域のサイズを指定
                    drawRect(color = Color.White, size = size, blendMode = BlendMode.SrcOver)

                    // drawCircle(
                    //         color = Color(0xFFDFDFDF), // 円の色
                    //         radius = size.minDimension / 2, // 円の半径、描画領域の半分のサイズ
                    //         center = center // 描画位置（中央）
                    // )
                }
            }
        }
    }
}
