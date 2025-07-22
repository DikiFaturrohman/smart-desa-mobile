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

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(
    // Terima aksi untuk logout dari navigasi utama
    onLogout: () -> Unit
) {
    // NavController ini HANYA untuk navigasi antar tab di bottom bar
    val bottomNavController = rememberNavController()

    val items = listOf(
        BottomNavItem("Home", Screen.Home.route, Icons.Default.Home),
        BottomNavItem("Layanan", Screen.Layanan.route, Icons.Default.MiscellaneousServices),
        BottomNavItem("Pengajuan", Screen.Pengajuan.route, Icons.Default.Description),
        BottomNavItem("Profil", Screen.Profile.route, Icons.Default.Person)
    )

    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by bottomNavController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                items.forEach { item ->
                    NavigationBarItem(
                        selected = currentDestination?.hierarchy?.any { it.route == item.route } == true,
                        onClick = {
                            bottomNavController.navigate(item.route) {
                                popUpTo(bottomNavController.graph.findStartDestination().id) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = { Icon(imageVector = item.icon, contentDescription = item.title) },
                        label = { Text(text = item.title) }
                    )
                }
            }
        }
    ) { innerPadding ->
        // NavHost ini HANYA mengatur konten dari bottom navigation
        NavHost(
            navController = bottomNavController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                // Teruskan aksi onLogout ke HomeScreen
                HomeScreen(onLogout = onLogout)
            }
            composable(Screen.Profile.route) {
                ProfileScreen()
            }
            composable(Screen.Layanan.route) { LayananScreen() }
            composable(Screen.Pengajuan.route) { PengajuanScreen() }
        }
    }
}