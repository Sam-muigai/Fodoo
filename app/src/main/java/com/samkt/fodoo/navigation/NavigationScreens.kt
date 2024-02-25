package com.samkt.fodoo.navigation

sealed class NavigationScreens(val route: String) {
    data object OnBoardingScreen : NavigationScreens("onboarding")

    data object HomeScreen : NavigationScreens("home_screen")

    data object Authentication : NavigationScreens("authentication")

    data object SignUpScreen : NavigationScreens("sign_up")

    data object LoginScreen : NavigationScreens("sign_up")
}
