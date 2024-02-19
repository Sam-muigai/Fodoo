package com.samkt.fodoo.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.samkt.fodoo.screens.onBoarding.OnBoardingScreen

@Composable
fun App() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavigationScreens.OnBoardingScreen.route,
    ) {
        composable(NavigationScreens.OnBoardingScreen.route) {
            OnBoardingScreen(
                navController = navController,
            )
        }
    }
}
