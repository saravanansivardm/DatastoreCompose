package com.example.datastorecompose.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.datastorecompose.HomeScreen
import com.example.datastorecompose.auth.ForgotPasswordScreen
import com.example.datastorecompose.auth.LoginScreen
import com.example.datastorecompose.auth.RegisterScreen
import com.example.datastorecompose.auth.VerificationScreen

@Composable
fun LoginNavigationHost(
    navController: NavHostController,
    navBarNavController: NavController,
    s2: String?
) {
    Log.e("s2_root_log", s2.toString())

    val s23 = if (s2 == "0") {
        Screen.LoginScreen.route
    } else if (s2 == "1") {
        Screen.Verification.route
    } else if (s2 == "3") {
        Screen.HomeScreen.route
    } else if (s2 == "4") {
        Screen.HomeScreen.route
    } else if (s2 == "5") {
        Screen.ForgotPassword.route
    } else {
        Screen.LoginScreen.route
    }
    NavHost(
        navController = navController,
        startDestination = s23
    ) {
        composable(
            route = Screen.LoginScreen.route,
            content = {
                LoginScreen(
                    navController = navController,
                    onClick = {}
                )
            })
        composable(
            route = Screen.RegisterScreen.route,
            content = { RegisterScreen(navController = navController) })

        composable(
            route = Screen.ForgotPassword.route,
            content = { ForgotPasswordScreen(navController = navController) })

        composable(
            route = Screen.Verification.route,
            content = { VerificationScreen(navController = navController) })

        composable(
            route = Screen.HomeScreen.route,
            content = {
                HomeScreen()
            })
    }
}