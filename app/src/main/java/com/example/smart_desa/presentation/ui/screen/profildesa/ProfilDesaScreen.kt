package com.example.smart_desa.presentation.ui.screen.profildesa

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Map
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

// Data class untuk mempermudah pengelolaan item menu
data class ProfilDesaMenuItem(
    val title: String,
    val icon: ImageVector
)

/**
 * Halaman yang menampilkan sub-menu dari Profil Desa.
 *
 * @param onBackPress Callback untuk kembali ke halaman sebelumnya.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfilDesaScreen(
    onBackPress: () -> Unit,
    onNavigateToVisiMisi: () -> Unit,
    onNavigateToSejarah: () -> Unit,
    onNavigateToGeografis: () -> Unit,
    onNavigateToGambaranUmum: () -> Unit
) {
    // Daftar pilihan menu untuk Profil Desa
    val profilDesaOptions = listOf(
        ProfilDesaMenuItem("Visi Misi", Icons.Default.Flag),
        ProfilDesaMenuItem("Sejarah", Icons.Default.History),
        ProfilDesaMenuItem("Geografis", Icons.Default.Map),
        ProfilDesaMenuItem("Gambaran Umum", Icons.Default.Description)
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Profil Desa") },
                navigationIcon = {
                    IconButton(onClick = onBackPress) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Kembali"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF00BFA5),
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(profilDesaOptions) { menuItem ->
                ProfilDesaMenuItemCard(menuItem = menuItem,
                    onNavigateToVisiMisi = onNavigateToVisiMisi,
                    onNavigateToSejarah = onNavigateToSejarah,
                    onNavigateToGeografis = onNavigateToGeografis,
                    onNavigateToGambaranUmum = onNavigateToGambaranUmum)
            }
        }
    }
}

/**
 * Composable untuk menampilkan setiap item menu dalam bentuk Card.
 *
 * @param menuItem Data item menu yang akan ditampilkan.
 */
@Composable
private fun ProfilDesaMenuItemCard(
    menuItem: ProfilDesaMenuItem,
                                       onNavigateToVisiMisi: () -> Unit,
                                       onNavigateToSejarah: () -> Unit,
                                       onNavigateToGeografis: () -> Unit,
                                       onNavigateToGambaranUmum: () -> Unit) {
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                when (menuItem.title) {
                    "Visi Misi" -> onNavigateToVisiMisi()
                    "Sejarah" -> onNavigateToSejarah()
                    "Geografis" -> onNavigateToGeografis()
                    "Gambaran Umum" -> onNavigateToGambaranUmum()
                    else -> {
                        Toast
                            .makeText(context, "Membuka ${menuItem.title}", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            },
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = menuItem.icon,
                contentDescription = null,
                tint = Color(0xFF00BFA5)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = menuItem.title,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.weight(1f),
                fontWeight = FontWeight.SemiBold
            )
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = "Lanjutkan",
                tint = Color.Gray
            )
        }
    }
}