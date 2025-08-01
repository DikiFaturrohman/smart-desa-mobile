package com.example.smart_desa.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.smart_desa.presentation.MainScreen
import com.example.smart_desa.presentation.navigation.Screen
import com.example.smart_desa.presentation.ui.screen.auth.forgotpassword.ForgotPasswordScreen
import com.example.smart_desa.presentation.ui.screen.auth.login.LoginScreen
import com.example.smart_desa.presentation.ui.screen.auth.register.RegisterScreen
import com.example.smart_desa.presentation.ui.screen.bumdes.BumdesScreen
import com.example.smart_desa.presentation.ui.screen.dokumen.UnggahDokumenScreen
import com.example.smart_desa.presentation.ui.screen.galeri.GaleriScreen
import com.example.smart_desa.presentation.ui.screen.pengajuan.ProgressPemohonScreen
import com.example.smart_desa.presentation.ui.screen.profildesa.ProfilDesaScreen
import com.example.smart_desa.presentation.ui.screen.profile.EditProfileScreen
import com.example.smart_desa.presentation.ui.screen.splash.SplashScreen
import com.example.smart_desa.presentation.ui.screen.berita.BeritaDetailScreen
import com.example.smart_desa.presentation.ui.screen.layanan.DetailLayananScreen
import java.net.URLDecoder
import java.nio.charset.StandardCharsets
import com.example.smart_desa.presentation.ui.screen.profildesa.VisiMisiScreen
import com.example.smart_desa.presentation.ui.screen.profildesa.SejarahScreen
import com.example.smart_desa.presentation.ui.screen.profildesa.GeografisScreen
import com.example.smart_desa.presentation.ui.screen.profildesa.GambaranUmumScreen
import com.example.smart_desa.presentation.ui.screen.bumdes.ProfilBumdesScreen
import com.example.smart_desa.presentation.ui.screen.bumdes.ProdukBumdesScreen


// Mengelola seluruh alur navigasi aplikasi menggunakan Jetpack Navigation.
@Composable
fun RootNavigation() {


    val navController = rememberNavController()


    NavHost(navController = navController, startDestination = "splash_screen") {

        // Layar pembuka aplikasi
        composable("splash_screen") {
            SplashScreen(
                onTimeout = {
                    // Arahkan ke Login setelah splash, hapus splash dari back stack
                    navController.navigate(Screen.Login.route) {
                        popUpTo("splash_screen") { inclusive = true }
                    }
                }
            )
        }

        // Alur autentikasi (Login, Register, Lupa Password)
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
                onNavigateToForgotPassword = {
                    navController.navigate(Screen.ForgotPassword.route)
                }
            )
        }

        composable(Screen.Register.route) {
            RegisterScreen(
                onRegisterSuccess = {
                    // Kembali ke Login setelah sukses
                    navController.popBackStack()
                },
                onBackPress = {
                    navController.popBackStack()
                }
            )
        }

        composable(Screen.ForgotPassword.route) {
                ForgotPasswordScreen(
                onBackPress = {
                    navController.popBackStack()
                }
            )
        }

        // Layar utama setelah pengguna login (termasuk bottom navigation)
        composable(Screen.Main.route) {
            MainScreen(
                onLogout = {
                    // Saat logout, kembali ke Login dan bersihkan semua back stack sebelumnya
                    navController.navigate(Screen.Login.route) {
                        popUpTo(Screen.Main.route) { inclusive = true }
                    }
                },
                onNavigateToProfilDesa = {
                    navController.navigate(Screen.ProfilDesa.route)
                },
                onNavigateToBumdes = {
                    navController.navigate(Screen.Bumdes.route)
                },
                onNavigateToGaleri = {
                    navController.navigate(Screen.Galeri.route)
                },
                onNavigateToUnggahDokumen = {
                    navController.navigate(Screen.UnggahDokumen.route)
                },
                onNavigateToEditProfile = {
                    navController.navigate(Screen.EditProfile.route)
                },
                onNavigateToDetailPengajuan = { id ->
                    navController.navigate(Screen.ProgressPemohon.createRoute(id))
                },
                onNavigateToBeritaDetail = { beritaId ->
                    navController.navigate(Screen.BeritaDetail.createRoute(beritaId))
                },
                onNavigateToDetailLayanan = { layananTitle ->
                    navController.navigate(Screen.DetailLayanan.createRoute(layananTitle))
                }
            )
        }

        // Halaman-halaman detail yang bisa diakses dari MainScreen
        composable(Screen.ProfilDesa.route) {
            ProfilDesaScreen(
                onBackPress = { navController.popBackStack() },
                // Tambahkan navigasi ke halaman detail
                onNavigateToVisiMisi = { navController.navigate(Screen.VisiMisi.route) },
                onNavigateToSejarah = { navController.navigate(Screen.Sejarah.route) },
                onNavigateToGeografis = { navController.navigate(Screen.Geografis.route) },
                onNavigateToGambaranUmum = { navController.navigate(Screen.GambaranUmum.route) }
            )
        }

        composable(Screen.VisiMisi.route) {
            VisiMisiScreen(onBackPress = { navController.popBackStack() })
        }
        composable(Screen.Sejarah.route) {
            SejarahScreen(onBackPress = { navController.popBackStack() })
        }
        composable(Screen.Geografis.route) {
            GeografisScreen(onBackPress = { navController.popBackStack() })
        }
        composable(Screen.GambaranUmum.route) {
            GambaranUmumScreen(onBackPress = { navController.popBackStack() })
        }

        composable(Screen.Bumdes.route) {
            BumdesScreen(
                onBackPress = {
                    navController.popBackStack()
                },
                onNavigateToProfilBumdes = {
                    navController.navigate(Screen.ProfilBumdes.route)
                },
                onNavigateToProdukBumdes = {
                    navController.navigate(Screen.ProdukBumdes.route)
                }
            )
        }

        composable(Screen.ProfilBumdes.route) {
            ProfilBumdesScreen(onBackPress = { navController.popBackStack() })
        }

        composable(Screen.ProdukBumdes.route) {
            ProdukBumdesScreen(onBackPress = { navController.popBackStack() })
        }

        composable(Screen.Galeri.route) {
            GaleriScreen(
                onBackPress = {
                    navController.popBackStack()
                }
            )

        }
        composable(Screen.UnggahDokumen.route) {
            UnggahDokumenScreen(
                onBackPress = {
                    navController.popBackStack()
                }
            )

        }



        // Halaman detail pengajuan dengan argumen I
        composable(
            route = Screen.ProgressPemohon.route,
            arguments = listOf(navArgument("pengajuanId") { type = NavType.IntType })
        ) { backStackEntry ->
            val pengajuanId = backStackEntry.arguments?.getInt("pengajuanId") ?: -1
            ProgressPemohonScreen(
                pengajuanId = pengajuanId,
                onBackPress = {
                    navController.popBackStack()
                }
            )
        }

        composable(Screen.EditProfile.route) {
            EditProfileScreen(
                onBackPress = {
                    navController.popBackStack()
                },
                onSave = {
                    // Setelah menyimpan, kembali ke halaman sebelumnya
                    navController.popBackStack()
                }
            )
        }
        composable(
            route = Screen.BeritaDetail.route,
            arguments = listOf(navArgument("beritaId") { type = NavType.IntType })
        ) { backStackEntry ->
            val beritaId = backStackEntry.arguments?.getInt("beritaId") ?: 0
            BeritaDetailScreen(
                beritaId = beritaId,
                onBackPress = { navController.popBackStack() }
            )
        }
        composable(
            route = Screen.DetailLayanan.route,
            arguments = listOf(navArgument("layananTitle") { type = NavType.StringType })
        ) { backStackEntry ->
            // --- UBAH BAGIAN INI ---
            val encodedTitle = backStackEntry.arguments?.getString("layananTitle") ?: "Layanan"
            val title = URLDecoder.decode(encodedTitle, StandardCharsets.UTF_8.toString())
            // -------------------------

            DetailLayananScreen(
                layananTitle = title, // Gunakan title yang sudah di-decode
                onBackPress = { navController.popBackStack() },
                onAjukan = { navController.popBackStack() }
            )
        }
        }
}