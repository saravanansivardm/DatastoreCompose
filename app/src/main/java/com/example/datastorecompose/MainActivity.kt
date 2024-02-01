package com.example.datastorecompose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.datastorecompose.navigation.LoginNavigationHost
import com.example.datastorecompose.ui.theme.YellowColor
import com.example.datastorecompose.viewmodel.auth.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: SplashViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(), color = YellowColor
            ) {
                val s2 = viewModel.getNamePref()
                Log.e("s2_main_log", s2.toString())

                val navController = rememberNavController()
                val navBarNavController = rememberNavController()
                LoginNavigationHost(navController, navBarNavController, s2)
            }
        }
    }
}
