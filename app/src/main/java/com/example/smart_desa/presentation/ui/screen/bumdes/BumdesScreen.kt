package com.example.smart_desa.presentation.ui.screen.bumdes

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

// Data class untuk mempermudah pengelolaan item menu (bisa pakai ulang dari ProfilDesa)
data class BumdesMenuItem(
    val title: String,
    val icon: ImageVector
)

/**
 * @param onBackPress Callback untuk kembali ke halaman sebelumnya.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BumdesScreen(
    onBackPress: () -> Unit
) {
    val bumdesOptions = listOf(
        BumdesMenuItem("Profil BUMDES", Icons.Default.Info),
        BumdesMenuItem("Produk BUMDES", Icons.Default.Category)
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("BUMDES") },
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
            items(bumdesOptions) { menuItem ->
                BumdesMenuItemCard(menuItem = menuItem)
            }
        }
    }
}

/**
 * Composable untuk menampilkan setiap item menu BUMDES dalam bentuk Card.
 */
@Composable
private fun BumdesMenuItemCard(menuItem: BumdesMenuItem) {
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                // Aksi saat item diklik, untuk saat ini hanya menampilkan Toast
                Toast
                    .makeText(context, "Membuka ${menuItem.title}", Toast.LENGTH_SHORT)
                    .show()
                // TODO: Navigasi ke halaman detail sesuai menuItem.title
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