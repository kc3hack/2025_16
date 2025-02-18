package com.example.test
import com.example.test.SleepTimer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.test.ui.theme.TestTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TestTheme {
                val navController = rememberNavController() // NavControllerの取得
                val screenState = remember { mutableStateOf(ScreenState.First) }
                NavHost(navController = navController, startDestination = "home") { // 最初の画面設定
                    composable("home") {
                        HomeScreen(navController) // HomeScreenを表示
                    }
                    composable("sleep") {
                        SleepTimer(onNavigateBack = {
                            navController.popBackStack() // 戻るボタンの機能
                        })
                    }

                }
                Surface() {
                    BottomAppBarExample(screenState)}
            }
        }
    }
}

@Composable
fun HomeScreen(navController: NavHostController) {
    Button(
        onClick = { navController.navigate("sleep") }, // ボタンを押すとsecond画面に遷移
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "Go to Sleep Screen")
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TestTheme {
        // HomeScreen(navController)
    }
}
