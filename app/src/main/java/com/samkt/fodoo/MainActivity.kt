package com.samkt.fodoo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.samkt.fodoo.screens.signUp.SignUpScreen
import com.samkt.fodoo.ui.theme.FodooTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FodooTheme {
                val navController = rememberNavController()
                SignUpScreen(navController = navController)
            }
        }
    }
}
