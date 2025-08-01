package com.example.smart_desa.presentation.ui.screen.bumdes

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.smart_desa.R

data class Produk(val nama: String, val harga: String, val gambarRes: Int)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProdukBumdesScreen(onBackPress: () -> Unit) {
    val daftarProduk = listOf(
        Produk("Nanas Madu Subang", "Rp 15.000 / buah", R.drawable.berita_subang),
        Produk("Keripik Nanas", "Rp 25.000 / pak", R.drawable.berita_subang),
        Produk("Dodol Nanas", "Rp 20.000 / kotak", R.drawable.berita_subang),
        Produk("Pupuk Organik", "Rp 50.000 / karung", R.drawable.berita_subang)
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Produk BUMDES") },
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
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(daftarProduk) { produk ->
                ProdukCard(produk = produk)
            }
        }
    }
}

@Composable
fun ProdukCard(produk: Produk) {
    Card(
        elevation = CardDefaults.cardElevation(4.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        Column {
            Image(
                painter = painterResource(id = produk.gambarRes),
                contentDescription = produk.nama,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier.padding(12.dp)) {
                Text(text = produk.nama, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = produk.harga, style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
            }
        }
    }
}