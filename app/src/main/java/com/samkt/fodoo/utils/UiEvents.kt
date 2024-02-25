package com.samkt.fodoo.utils

sealed class UiEvents {
    data class Navigate(val route: String) : UiEvents()

    data class ShowSnackBar(val message: String) : UiEvents()
}
