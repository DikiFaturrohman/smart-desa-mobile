package com.example.smart_desa.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.smart_desa.presentation.navigation.Screen
import com.example.smart_desa.presentation.ui.screen.auth.login.LoginScreen
import com.example.smart_desa.presentation.ui.screen.auth.register.RegisterScreen
import com.example.smart_desa.presentation.ui.screen.home.HomeScreen

@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Login.route) {
        composable(route = Screen.Login.route) {
            LoginScreen(navController = navController)
        }
        composable(route = Screen.Register.route) {
            RegisterScreen(navController = navController)
        }
        composable(route = Screen.Home.route) {
            HomeScreen(navController = navController)
        }
    }
}