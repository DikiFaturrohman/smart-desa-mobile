package com.example.smart_desa.presentation.ui.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smart_desa.R // Pastikan R di-import

@Composable
fun HomeScreen(
    onLogout: () -> Unit // Aksi logout tetap ada jika dibutuhkan di tempat lain
) {
    val primaryColor = Color(0xFF00BFA5)
    val backgroundColor = Color(0xFF00BFA5) // Warna latar belakang sedikit hijau

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(primaryColor, backgroundColor),
                    startY = 0f,
                    endY = 1000f // Sesuaikan nilai ini untuk panjang gradient
                )
            )
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp)
        ) {
            // Bagian Header
            item {
                HomeHeader()
                Spacer(modifier = Modifier.height(24.dp))
            }

            // Bagian Menu Grid
            item {
                MenuGridSection()
                Spacer(modifier = Modifier.height(24.dp))
            }

            // Bagian Info Grafis
            item {
                InfoGrafisSection()
                Spacer(modifier = Modifier.height(24.dp))
            }

            // Bagian Berita
            item {
                BeritaSection()
            }
        }
    }
}

@Composable
fun HomeHeader() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Ganti dengan logo desa Anda
        Image(
            painter = painterResource(id = R.drawable.lambang_kabupaten_subang),
            contentDescription = "Logo Desa",
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Text(
                text = "Desa Sukamandi",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            Text(
                text = "Kab. Subang",
                color = Color.White.copy(alpha = 0.8f),
                fontSize = 14.sp
            )
        }
    }
}

@Composable
fun MenuGridSection() {
    val menuItems = listOf(
        MenuItem("Profil Desa", Icons.Default.AccountBalance),
        MenuItem("Potensi Desa", Icons.Default.Landscape),
        MenuItem("Program Desa", Icons.Default.Event),
        MenuItem("Pemerintah Desa", Icons.Default.Groups),
        MenuItem("Unggah Dokumen", Icons.Default.CloudUpload),
        MenuItem("BUMDES", Icons.Default.Store),
        MenuItem("Galeri", Icons.Default.PhotoLibrary)
    )

    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Kita bagi item menjadi beberapa baris, setiap baris berisi 4 item
            menuItems.chunked(4).forEach { rowItems ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // Tampilkan setiap item di dalam baris
                    rowItems.forEach { item ->
                        Box(modifier = Modifier.weight(1f)) {
                            MenuIconItem(menuItem = item, onClick = { /* TODO: Handle menu click */ })
                        }
                    }
                    // Jika item dalam satu baris kurang dari 4, tambahkan Spacer kosong
                    // agar tata letak tetap rapi
                    repeat(4 - rowItems.size) {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }

        }}
}

@Composable
fun MenuIconItem(menuItem: MenuItem, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable(onClick = onClick)
    ) {
        Box(
            modifier = Modifier
                .size(56.dp)
                .clip(CircleShape)
                .background(Color(0xFFF0F0F0)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = menuItem.icon,
                contentDescription = menuItem.title,
                tint = Color.Gray,
                modifier = Modifier.size(28.dp)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = menuItem.title,
            fontSize = 12.sp,
            textAlign = TextAlign.Center,
            lineHeight = 14.sp
        )
    }
}

@Composable
fun InfoGrafisSection() {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Info Grafis",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(12.dp))
        Card(
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            // Ganti dengan gambar banner Anda
            Image(
                painter = painterResource(id = R.drawable.infografis),
                contentDescription = "Info Grafis Vaksinasi",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Composable
fun BeritaSection() {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Berita",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(12.dp))
        Card(
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(4.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            modifier = Modifier.clickable { /* TODO: Handle berita click */ }
        ) {
            // Ganti dengan gambar berita Anda
            Image(
                painter = painterResource(id = R.drawable.berita_subang),
                contentDescription = "Berita Kecelakaan",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
                contentScale = ContentScale.Crop
            )
            // Anda bisa menambahkan judul berita di bawah gambar jika perlu
            // Text(text = "Judul Berita", modifier = Modifier.padding(16.dp))
        }
    }
}