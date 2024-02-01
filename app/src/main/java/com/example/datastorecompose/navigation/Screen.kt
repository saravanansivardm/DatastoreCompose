package com.example.datastorecompose.navigation

sealed class Screen(
    val route: String,
) {
    object LoginScreen : Screen("login_screen")
    object RegisterScreen : Screen("register_screen")
    object ForgotPassword : Screen("forgot_password_screen")
    object Verification : Screen("verification_screen")
    object HomeScreen : Screen("home_screen")
}

