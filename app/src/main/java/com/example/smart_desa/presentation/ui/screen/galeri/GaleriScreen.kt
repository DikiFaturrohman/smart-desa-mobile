package com.example.smart_desa.presentation.ui.screen.galeri

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.PhotoLibrary
import androidx.compose.material.icons.filled.Videocam
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

data class GaleriMenuItem(
    val title: String,
    val icon: ImageVector
)

/**
 * @param onBackPress Callback untuk kembali ke halaman sebelumnya.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GaleriScreen(
    onBackPress: () -> Unit
) {
    // Daftar pilihan menu untuk Galeri
    val galeriOptions = listOf(
        GaleriMenuItem("Foto", Icons.Default.PhotoLibrary),
        GaleriMenuItem("Video", Icons.Default.Videocam)
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Galeri") },
                navigationIcon = {
                    IconButton(onClick = onBackPress) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Kembali"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
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
            items(galeriOptions) { menuItem ->
                GaleriMenuItemCard(menuItem = menuItem)
            }
        }
    }
}


@Composable
private fun GaleriMenuItemCard(menuItem: GaleriMenuItem) {
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {

                Toast
                    .makeText(context, "Membuka ${menuItem.title}", Toast.LENGTH_SHORT)
                    .show()
                // TODO: Navigasi ke halaman detail foto atau video
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
                tint = MaterialTheme.colorScheme.primary
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