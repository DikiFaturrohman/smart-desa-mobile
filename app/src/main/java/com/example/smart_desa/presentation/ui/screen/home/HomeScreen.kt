package com.example.smart_desa.presentation.ui.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
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
import com.example.smart_desa.R

// Data class untuk setiap item berita
data class BeritaItem(
    val id: Int,
    val title: String,
    val imageRes: Int
)

/**
 * Menampilkan header, menu grid, info grafis, dan berita dalam layout vertikal
 * @param onLogout Callback function untuk aksi logout (disediakan untuk kebutuhan masa depan)
 */
@Composable
fun HomeScreen(
    onLogout: () -> Unit,
    onNavigateToProfilDesa: () -> Unit,
    onNavigateToBumdes: () -> Unit,
    onNavigateToGaleri: () -> Unit,
    onNavigateToUnggahDokumen: () -> Unit,
    onNavigateToBeritaDetail: (Int) -> Unit
) {



    val primaryColor = Color(0xFF00BFA5)
    val backgroundColor = Color(0xFF00BFA5)

    // Container
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(

                brush = Brush.verticalGradient(
                    colors = listOf(primaryColor, backgroundColor),
                    startY = 0f,
                    endY = 1000f
                )
            )
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp) // Memberi jarak antar semua item utama
        ) {
            // Header
            item {
                HomeHeader()
            }

            // Menu Grid
            item {
                MenuGridSection(
                    onNavigateToProfilDesa = onNavigateToProfilDesa,
                    onNavigateToBumdes = onNavigateToBumdes,
                    onNavigateToGaleri = onNavigateToGaleri,
                    onNavigateToUnggahDokumen = onNavigateToUnggahDokumen
                )
            }

            // Info Grafis
            item {
                InfoGrafisSection()
            }

            // Berita
            item {
                BeritaSection(onNavigateToBeritaDetail = onNavigateToBeritaDetail) // Teruskan callback
            }
        }
    }
}

/**
 * Header Home
 * Berisi logo desa dan informasi nama desa dengan kabupaten
 */
@Composable
fun HomeHeader() {
    // Row untuk layout horizontal logo dan text
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Logo
        Image(
            painter = painterResource(id = R.drawable.lambang_kabupaten_subang),
            contentDescription = "Logo Desa",
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.width(12.dp))

        // Informasi Desa
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

/**
 * Menu ditampilkan dalam Card dengan arrangement grid custom (4 kolom per baris)
 */
@Composable
fun MenuGridSection(
    onNavigateToProfilDesa: () -> Unit,
    onNavigateToBumdes: () -> Unit,
    onNavigateToGaleri: () -> Unit,
    onNavigateToUnggahDokumen: () -> Unit
) {

    val menuItems = listOf(
        MenuItem("Profil Desa", Icons.Default.AccountBalance),
        MenuItem("Informasi", Icons.Default.Info),
        MenuItem("Agenda", Icons.Default.Event),
        MenuItem("Download", Icons.Default.Download),
        MenuItem("Unggah Dokumen", Icons.Default.CloudUpload),
        MenuItem("BUMDES", Icons.Default.Store),
        MenuItem("Galeri", Icons.Default.PhotoLibrary)
    )

    // Card Container
    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Arrangement
            menuItems.chunked(4).forEach { rowItems ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    rowItems.forEach { item ->
                        Box(modifier = Modifier.weight(1f)) {
                            MenuIconItem(
                                menuItem = item,
                                onClick = {
                                    when (item.title) {
                                        "Profil Desa" -> onNavigateToProfilDesa()
                                        "BUMDES" -> onNavigateToBumdes()
                                        "Galeri" -> onNavigateToGaleri()
                                        "Unggah Dokumen" -> onNavigateToUnggahDokumen()
                                        else -> {
                                            // TODO: Handle menu click untuk item lainnya
                                        }
                                    }
                                }
                            )
                        }
                    }

                    // Fill Empty
                    repeat(4 - rowItems.size) {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        }
    }
}

/**
 * @param menuItem Data class berisi title dan icon untuk menu
 * @param onClick Callback function yang dipanggil saat item diklik
 */
@Composable
fun MenuIconItem(menuItem: MenuItem, onClick: () -> Unit) {
    // Column untuk layout vertikal icon dan text
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable(onClick = onClick)
    ) {
        // Icon Container
        Box(
            modifier = Modifier
                .size(56.dp)
                .clip(CircleShape)
                .background(Color(0xFFF0F0F0)),
            contentAlignment = Alignment.Center
        ) {
            // Icon dengan styling dan ukuran custom
            Icon(
                imageVector = menuItem.icon,
                contentDescription = menuItem.title,
                tint = Color.Gray,
                modifier = Modifier.size(28.dp)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))
        // Label
        Text(
            text = menuItem.title,
            fontSize = 12.sp,
            textAlign = TextAlign.Center,
            lineHeight = 14.sp
        )
    }
}

/**
 * Section Info Grafis
 * Berisi judul section dan gambar infografis dalam Card
 */
@Composable
fun InfoGrafisSection() {

    val infografisItems = listOf(
        R.drawable.infografis,
        R.drawable.infograf,
        R.drawable.info
    )

    val pagerState = rememberPagerState(pageCount = {
        infografisItems.size
    })

    val primaryColor = Color(0xFF00BFA5)

    Column(modifier = Modifier.fillMaxWidth()) {
        // Judul
        Text(
            text = "Info Grafis",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(12.dp))

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 8.dp),
            pageSpacing = 16.dp

            // Card Infografis
        ) { page ->
            // Setiap item di dalam Pager adalah sebuah Card
            Card(
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(4.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = infografisItems[page]),
                    contentDescription = "Info Grafis ${page + 1}",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .clip(RoundedCornerShape(16.dp)),
                    contentScale = ContentScale.Crop
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Indikator
        Row(
            Modifier
                .height(20.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {

            repeat(pagerState.pageCount) { iteration ->

                val color =
                    if (pagerState.currentPage == iteration) Color.Gray else Color.LightGray

                val size = if (pagerState.currentPage == iteration) 10.dp else 8.dp

                Box(
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .clip(CircleShape)
                        .background(color)
                        .size(size)
                )
            }
        }
    }
}

/**
 * Berita
 * Berisi judul section dan daftar berita dalam Card yang clickable
 */
@Composable
fun BeritaSection(onNavigateToBeritaDetail: (Int) -> Unit) {
    // Daftar berita dummy
    val daftarBerita = listOf(
        BeritaItem(1, "Kecelakaan Maut di Jalur Pantura Subang, 2 Orang Meninggal Dunia", R.drawable.berita_subang),
        BeritaItem(2, "Pemdes Sukamandi Gelar Pelatihan UMKM untuk Ibu-Ibu PKK", R.drawable.berita_subang),
        BeritaItem(3, "Jalan Desa Selesai Diperbaiki, Warga Sambut Gembira", R.drawable.berita_subang)
    )

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp) // Jarak antar kartu berita
    ) {
        // Judul
        Text(
            text = "Berita",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )

        // Looping untuk menampilkan setiap berita
        daftarBerita.forEach { berita ->
            // Panggil BeritaCard dengan callback
            BeritaCard(
                berita = berita,
                onClick = { onNavigateToBeritaDetail(berita.id) }
            )
        }
    }
}

/**
 * Composable untuk menampilkan satu kartu berita.
 * @param berita Data berita yang akan ditampilkan.
 */
@Composable
fun BeritaCard(berita: BeritaItem, onClick: () -> Unit) {
    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = Modifier.clickable(onClick = onClick)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
        ) {
            Image(
                painter = painterResource(id = berita.imageRes),
                contentDescription = berita.title,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            // Gradient Overlay untuk membuat teks lebih mudah dibaca
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.8f)),
                            startY = 300f // Mulai gradien dari 300px ke bawah
                        )
                    )
            )

            // Teks Judul Berita
            Text(
                text = berita.title,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(16.dp)
            )
        }
    }
}