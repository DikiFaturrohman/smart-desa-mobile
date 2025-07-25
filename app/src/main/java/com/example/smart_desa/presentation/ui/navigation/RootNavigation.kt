package com.example.smart_desa.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.smart_desa.presentation.MainScreen
import com.example.smart_desa.presentation.navigation.Screen
import com.example.smart_desa.presentation.ui.screen.auth.forgotpassword.ForgotPasswordScreen
import com.example.smart_desa.presentation.ui.screen.auth.login.LoginScreen
import com.example.smart_desa.presentation.ui.screen.auth.register.RegisterScreen
import com.example.smart_desa.presentation.ui.screen.bumdes.BumdesScreen
import com.example.smart_desa.presentation.ui.screen.profildesa.ProfilDesaScreen
import com.example.smart_desa.presentation.ui.screen.splash.SplashScreen

/**
 * Composable utama untuk sistem navigasi aplikasi Smart Desa
 *
 * Fungsi ini mengelola navigasi antar layar dalam aplikasi dengan menggunakan
 * Jetpack Navigation Compose. Mengatur alur navigasi dari splash screen,
 * proses authentication, hingga main screen dengan proper back stack management.
 *
 * Navigation Flow:
 * 1. SplashScreen -> LoginScreen (clear back stack)
 * 2. LoginScreen -> RegisterScreen (add to back stack)
 * 3. LoginScreen -> MainScreen (clear back stack)
 * 4. RegisterScreen -> LoginScreen (pop back stack)
 * 5. MainScreen -> LoginScreen (clear back stack for logout)
 */
@Composable
fun RootNavigation() {
    // === NAVIGATION CONTROLLER INITIALIZATION ===
    // rememberNavController() membuat NavController yang survive recomposition
    // NavController mengelola navigation state dan back stack
    val navController = rememberNavController()

    // === NAV HOST SETUP ===
    // NavHost adalah container utama yang menampung semua destination
    // startDestination menentukan layar pertama yang ditampilkan saat app dibuka
    NavHost(navController = navController, startDestination = "splash_screen") {

        // === SPLASH SCREEN DESTINATION ===
        // Layar pembuka aplikasi yang ditampilkan saat pertama kali membuka app
        composable("splash_screen") {
            SplashScreen(
                onTimeout = {
                    // === NAVIGATION LOGIC: SPLASH TO LOGIN ===
                    // Setelah timeout splash screen, navigasi ke Login screen
                    // popUpTo dengan inclusive=true menghapus splash_screen dari back stack
                    // Ini mencegah user kembali ke splash screen dengan back button
                    navController.navigate(Screen.Login.route) {
                        popUpTo("splash_screen") { inclusive = true }
                    }
                }
            )
        }

        // === LOGIN SCREEN DESTINATION ===
        // Layar authentication untuk user login menggunakan NIK dan password
        composable(Screen.Login.route) {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(Screen.Main.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                },
                onNavigateToRegister = {
                    navController.navigate(Screen.Register.route)
                },
                // Tambahkan callback dan aksinya di sini
                onNavigateToForgotPassword = {
                    // Navigasi ke halaman lupa sandi
                    navController.navigate(Screen.ForgotPassword.route)
                }
            )
        }

        // === REGISTER SCREEN DESTINATION ===
        // Layar pendaftaran akun baru untuk user yang belum memiliki akun
        composable(Screen.Register.route) {
            RegisterScreen(
                onRegisterSuccess = {
                    // === NAVIGATION LOGIC: REGISTER SUCCESS BACK TO LOGIN ===
                    // Kembali ke layar login setelah registrasi berhasil
                    // popBackStack() menghapus register screen dari back stack
                    // dan kembali ke destination sebelumnya (login screen)
                    navController.popBackStack()
                },
                onBackPress = {
                    // === NAVIGATION LOGIC: BACK BUTTON PRESSED ===
                    // Menangani aksi back button di register screen
                    // popBackStack() kembali ke login screen
                    navController.popBackStack()
                }
            )
        }

        // ==========================================================
        // === FORGOT PASSWORD SCREEN DESTINATION ===
        // Layar untuk mereset password melalui nomor telepon
        composable(Screen.ForgotPassword.route) {
                ForgotPasswordScreen(
                onBackPress = {
                    // Aksi saat tombol kembali ditekan adalah kembali ke layar sebelumnya (Login)
                    navController.popBackStack()
                }
            )
        }

        // === MAIN SCREEN DESTINATION ===
        // Layar utama aplikasi yang berisi home screen dan fitur-fitur utama
        composable(Screen.Main.route) {
            MainScreen(
                onLogout = {
                    // === NAVIGATION LOGIC: LOGOUT TO LOGIN ===
                    // Navigasi kembali ke layar Login saat user logout
                    // popUpTo dengan inclusive=true menghapus main screen dari back stack
                    // Ini memastikan user tidak bisa kembali ke main screen
                    // setelah logout tanpa login ulang (security measure)
                    navController.navigate(Screen.Login.route) {
                        popUpTo(Screen.Main.route) { inclusive = true }
                    }
                },
                onNavigateToProfilDesa = {
                    navController.navigate(Screen.ProfilDesa.route)
                },
                onNavigateToBumdes = {
                    navController.navigate(Screen.Bumdes.route)
                }
            )
        }

        composable(Screen.ProfilDesa.route) {
            ProfilDesaScreen(
                onBackPress = {
                    navController.popBackStack()
                }
            )
        }
        composable(Screen.Bumdes.route) {
            BumdesScreen(
                onBackPress = {
                    navController.popBackStack()
                }
            )
        }
    }
}