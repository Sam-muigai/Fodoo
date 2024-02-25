package com.samkt.fodoo.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.samkt.fodoo.screens.home.HomeScreen
import com.samkt.fodoo.screens.onBoarding.OnBoardingScreen
import com.samkt.fodoo.screens.signUp.SignUpScreen

@Composable
fun App() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavigationScreens.Authentication.route,
    ) {
        composable(NavigationScreens.OnBoardingScreen.route) {
            OnBoardingScreen(
                navController = navController,
            )
        }
        navigation(
            startDestination = NavigationScreens.SignUpScreen.route,
            route = NavigationScreens.Authentication.route,
        ) {
            composable(NavigationScreens.SignUpScreen.route) {
                SignUpScreen(
                    navController = navController,
                )
            }
        }
        composable(NavigationScreens.HomeScreen.route) {
            HomeScreen(
                navController = navController,
            )
        }
    }
}
