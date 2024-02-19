package com.samkt.fodoo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.samkt.fodoo.navigation.App
import com.samkt.fodoo.ui.theme.FodooTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FodooTheme {
                App()
            }
        }
    }
}
