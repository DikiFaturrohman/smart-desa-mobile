package com.example.smart_desa.presentation.navigation

import java.net.URLEncoder
import java.nio.charset.StandardCharsets

sealed class Screen(val route: String) {
    // Rute untuk alur Autentikasi
    object Login : Screen("login_screen")
    object Register : Screen("register_screen")
    data object ForgotPassword : Screen("forgot_password_screen")
    data object ProfilDesa : Screen("profil_desa_screen")
    data object Bumdes : Screen("bumdes_screen")
    data object Galeri : Screen("galeri_screen")
    data object UnggahDokumen : Screen("unggah_dokumen_screen")
    data object EditProfile : Screen("edit_profile_screen")
    data object VisiMisi : Screen("visi_misi_screen")
    data object Sejarah : Screen("sejarah_screen")
    data object Geografis : Screen("geografis_screen")
    data object GambaranUmum : Screen("gambaran_umum_screen")
    data object ProfilBumdes : Screen("profil_bumdes_screen")
    data object ProdukBumdes : Screen("produk_bumdes_screen")
    data object Foto : Screen("foto_screen")
    data object Video : Screen("video_screen")

    data object ProgressPemohon: Screen("progress_pemohon_screen/{pengajuanId}") {
        fun createRoute(pengajuanId: Int) = "progress_pemohon_screen/$pengajuanId"
    }

    data object BeritaDetail: Screen("berita_detail_screen/{beritaId}") {
        fun createRoute(beritaId: Int) = "berita_detail_screen/$beritaId"
    }

    data object DetailLayanan : Screen("detail_layanan_screen/{layananTitle}") {
        fun createRoute(layananTitle: String): String {
            val encodedTitle = URLEncoder.encode(layananTitle, StandardCharsets.UTF_8.toString())
            return "detail_layanan_screen/$encodedTitle"
        }
    }

    // Rute untuk alur utama setelah login (termasuk bottom nav)
    object Main : Screen("main_screen")

    // Rute untuk tab di bottom nav
    object Home : Screen("home_tab")
    object Profile : Screen("profile_tab")
    object Layanan : Screen("layanan_tab")
    object Pengajuan : Screen("pengajuan_tab")
}
