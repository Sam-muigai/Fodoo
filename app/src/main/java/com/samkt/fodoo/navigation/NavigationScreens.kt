package com.samkt.fodoo.navigation

sealed class NavigationScreens(val route: String) {
    data object OnBoardingScreen : NavigationScreens("onboarding")
}
