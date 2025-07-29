/**
 * MainScreen.kt
 *
 * Komponen utama yang mengatur tampilan layar utama aplikasi Smart Desa
 * dengan bottom navigation bar untuk navigasi antar tab.
 *
 * File ini berisi:
 * - MainScreen composable yang mengatur layout utama dengan Scaffold
 * - Bottom navigation bar dengan 4 tab utama
 * - NavHost untuk mengelola navigasi antar screen
 *
 * @author Smart Desa Development Team
 */

package com.example.smart_desa.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MiscellaneousServices
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.smart_desa.presentation.navigation.Screen
import com.example.smart_desa.presentation.ui.navigation.BottomNavItem
import com.example.smart_desa.presentation.ui.screen.home.HomeScreen
import com.example.smart_desa.presentation.ui.screen.layanan.LayananScreen
import com.example.smart_desa.presentation.ui.screen.pengajuan.PengajuanScreen
import com.example.smart_desa.presentation.ui.screen.profile.ProfileScreen

/**
 * Suppresses warning untuk parameter Scaffold yang tidak digunakan
 * Annotation ini diperlukan karena kita menggunakan innerPadding dari Scaffold
 * tetapi Android Studio mendeteksi sebagai unused parameter
 */
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(
    /**
     * Callback function yang dipanggil ketika user melakukan logout
     * Function ini akan diteruskan ke HomeScreen untuk menangani aksi logout
     *
     * @param onLogout Lambda function yang akan dieksekusi saat logout
     */
    onLogout: () -> Unit,
    onNavigateToProfilDesa: () -> Unit,
    onNavigateToBumdes: () -> Unit,
    onNavigateToGaleri: () -> Unit,
    onNavigateToUnggahDokumen: () -> Unit,
    onNavigateToEditProfile: () -> Unit,
    onNavigateToDetailPengajuan: (Int) -> Unit,
    onNavigateToBeritaDetail: (Int) -> Unit
) {
    /**
     * NavController khusus untuk mengelola navigasi bottom navigation bar
     * Controller ini HANYA menangani perpindahan antar tab di bottom bar,
     * bukan untuk navigasi ke screen lain di luar main tabs
     */
    val bottomNavController = rememberNavController()

    /**
     * Daftar item-item yang akan ditampilkan di bottom navigation bar
     * Setiap item berisi:
     * - title: Nama tab yang ditampilkan
     * - route: Route navigasi yang sesuai dengan Screen enum
     * - icon: Icon Material Design yang akan ditampilkan
     */
    val items = listOf(
        BottomNavItem("Home", Screen.Home.route, Icons.Default.Home),
        BottomNavItem("Layanan", Screen.Layanan.route, Icons.Default.MiscellaneousServices),
        BottomNavItem("Pengajuan", Screen.Pengajuan.route, Icons.Default.Description),
        BottomNavItem("Profil", Screen.Profile.route, Icons.Default.Person)
    )

    /**
     * Scaffold adalah layout dasar Material Design 3 yang menyediakan
     * struktur umum untuk screen dengan app bar, bottom bar, floating action button, dll.
     * Di sini kita hanya menggunakan bottomBar dan content area
     */
    Scaffold(
        bottomBar = {
            /**
             * NavigationBar adalah komponen Material Design 3 untuk bottom navigation
             * Menggantikan BottomNavigation dari Material Design 2
             */
            NavigationBar {
                /**
                 * Mendapatkan back stack entry saat ini untuk menentukan tab mana yang aktif
                 * currentBackStackEntryAsState() memberikan state yang ter-update otomatis
                 * ketika navigasi berubah
                 */
                val navBackStackEntry by bottomNavController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                /**
                 * Iterasi semua item bottom navigation untuk membuat NavigationBarItem
                 */
                items.forEach { item ->
                    NavigationBarItem(
                        /**
                         * Menentukan apakah item ini sedang dipilih/aktif
                         * Menggunakan hierarchy untuk mengecek apakah route saat ini
                         * sesuai dengan route item ini
                         */
                        selected = currentDestination?.hierarchy?.any { it.route == item.route } == true,

                        /**
                         * Callback ketika item di-tap
                         * Navigasi ke route yang sesuai dengan konfigurasi:
                         * - popUpTo: Clear back stack sampai start destination
                         * - saveState: Simpan state screen sebelumnya
                         * - launchSingleTop: Hindari duplikasi screen di back stack
                         * - restoreState: Restore state ketika kembali ke screen ini
                         */
                        onClick = {
                            bottomNavController.navigate(item.route) {
                                popUpTo(bottomNavController.graph.findStartDestination().id) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },

                        /**
                         * Icon yang ditampilkan untuk setiap tab
                         * contentDescription untuk accessibility
                         */
                        icon = { Icon(imageVector = item.icon, contentDescription = item.title) },

                        /**
                         * Label text yang ditampilkan di bawah icon
                         */
                        label = { Text(text = item.title) }
                    )
                }
            }
        }
    ) { innerPadding ->
        /**
         * NavHost mengelola navigasi dan menampilkan composable yang sesuai
         * berdasarkan route yang aktif
         *
         * Parameter:
         * - navController: Controller untuk mengelola navigasi
         * - startDestination: Screen pertama yang ditampilkan (Home)
         * - modifier: Padding dari Scaffold untuk menghindari overlap dengan bottom bar
         */
        NavHost(
            navController = bottomNavController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            /**
             * Definisi route untuk Home screen
             * Meneruskan onLogout callback ke HomeScreen
             * karena HomeScreen mungkin memiliki tombol logout
             */
            composable(Screen.Home.route) {
                HomeScreen(
                    onLogout = onLogout,
                    onNavigateToProfilDesa = onNavigateToProfilDesa,
                    onNavigateToBumdes = onNavigateToBumdes,
                    onNavigateToGaleri = onNavigateToGaleri,
                    onNavigateToUnggahDokumen = onNavigateToUnggahDokumen,
                    onNavigateToBeritaDetail = onNavigateToBeritaDetail
                    )
            }

            /**
             * Definisi route untuk Profile screen
             * Screen untuk menampilkan dan mengedit profil user
             */
            composable(Screen.Profile.route) {
                ProfileScreen(onLogout = onLogout,
                    onNavigateToEditProfile = onNavigateToEditProfile)
            }

            /**
             * Definisi route untuk Layanan screen
             * Screen untuk menampilkan berbagai layanan desa yang tersedia
             */
            composable(Screen.Layanan.route) { LayananScreen() }

            /**
             * Definisi route untuk Pengajuan screen
             * Screen untuk melihat dan mengelola pengajuan yang telah dibuat user
             */
            composable(Screen.Pengajuan.route) {
                PengajuanScreen(
                    onNavigateToDetail = onNavigateToDetailPengajuan // TERUSKAN CALLBACK
                )
            }
        }
    }
}