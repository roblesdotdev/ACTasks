package com.roblesdotdev.act

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.roblesdotdev.act.core.ui.theme.ACTasksTheme
import com.roblesdotdev.act.login.ui.LoginScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ACTasksTheme {
                LoginScreen()
            }
        }
    }
}
