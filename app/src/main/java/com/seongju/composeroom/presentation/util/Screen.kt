package com.seongju.composeroom.presentation.util

sealed class Screen(val route: String) {

    object LoginScreen: Screen("login_screen")
    object SignUpScreen: Screen("sign_up_screen")

}