package com.example.smart_desa.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.smart_desa.presentation.MainScreen
import com.example.smart_desa.presentation.navigation.Screen

// Asumsikan Anda punya LoginScreen dan RegisterScreen yang sudah dimodifikasi
import com.example.smart_desa.presentation.ui.screen.auth.login.LoginScreen
import com.example.smart_desa.presentation.ui.screen.auth.register.RegisterScreen

@Composable
fun RootNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Login.route) {
        composable(Screen.Login.route) {
            LoginScreen(
                onLoginSuccess = {
                    // Navigasi ke MainScreen dan hapus semua layar sebelumnya dari back stack
                    navController.navigate(Screen.Main.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                },
                onNavigateToRegister = {
                    navController.navigate(Screen.Register.route)
                }
            )
        }
        composable(Screen.Register.route) {
            RegisterScreen(
                onRegisterSuccess = {
                    // Kembali ke layar login setelah register
                    navController.popBackStack()
                },
                onBackPress = {
                    navController.popBackStack()
                }
            )
        }
        composable(Screen.Main.route) {
            MainScreen(
                onLogout = {
                    // Navigasi kembali ke layar Login dan hapus semua layar sebelumnya
                    navController.navigate(Screen.Login.route) {
                        popUpTo(Screen.Main.route) { inclusive = true }
                    }
                }
            )
        }
    }
}