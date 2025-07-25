package com.example.smart_desa.presentation.navigation

sealed class Screen(val route: String) {
    // Rute untuk alur Autentikasi
    object Login : Screen("login_screen")
    object Register : Screen("register_screen")
    data object ForgotPassword : Screen("forgot_password_screen")
    data object ProfilDesa : Screen("profil_desa_screen")
    data object Bumdes : Screen("bumdes_screen")
    data object Galeri : Screen("galeri_screen")
    data object UnggahDokumen : Screen("unggah_dokumen_screen")

    data object ProgressPemohon: Screen("progress_pemohon_screen/{pengajuanId}") {
        fun createRoute(pengajuanId: Int) = "progress_pemohon_screen/$pengajuanId"
    }

    // Rute untuk alur utama setelah login (termasuk bottom nav)
    object Main : Screen("main_screen")

    // Rute untuk tab di bottom nav
    object Home : Screen("home_tab")
    object Profile : Screen("profile_tab")
    object Layanan : Screen("layanan_tab")
    object Pengajuan : Screen("pengajuan_tab")
}
