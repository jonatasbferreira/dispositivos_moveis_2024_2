package com.example.apppost2

import android.annotation.SuppressLint
import android.content.res.Resources.Theme
import android.graphics.drawable.Icon
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.apppost2.ui.screens.PostScreen
import com.example.apppost2.ui.screens.UserScreen
import com.example.apppost2.ui.theme.AppPost2Theme
import com.example.apppost2.ui.theme.Green80

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MainScreen()
        }

        enableFullscreenMode()
    }

    private fun enableFullscreenMode() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.decorView.setOnApplyWindowInsetsListener { _, insets ->
                window.insetsController?.apply {
                    hide(WindowInsets.Type.systemBars())
                    systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
                }
                insets
            }
        } else {
            @Suppress("DEPRECATION")
            window.decorView.systemUiVisibility = (
                    View.SYSTEM_UI_FLAG_FULLSCREEN or
                            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                            View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    )
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(){
    var selectTab by remember { mutableIntStateOf(0) }

    Scaffold(
        topBar = {
        },
        bottomBar = {
            BottomNavigation (
                backgroundColor = Color(0xFF0D47A1)
            ) {
                BottomNavigationItem(
                    selected = selectTab == 0,
                    onClick = {selectTab = 0},
                    label = {Text(text ="Usuários", color = Color.White)},
                    icon = {Icon(Icons.Default.Person, contentDescription = "Usuarios", tint = Color.White)}
                )

                BottomNavigationItem(
                    selected = selectTab == 1,
                    onClick = {selectTab = 1},
                    label = {Text(text ="Posts", color = Color.White)},
                    icon = {Icon(Icons.Default.List, contentDescription = "Posts", tint = Color.White)}
                )
            }
        }
    ) {
        when(selectTab){
            0 -> UserScreen()
            1 -> PostScreen()
        }
    }
}