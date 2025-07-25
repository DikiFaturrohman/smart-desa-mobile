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

/**
 * Composable utama untuk halaman Home aplikasi Smart Desa
 * Menampilkan header, menu grid, info grafis, dan berita dalam layout vertikal
 *
 * @param onLogout Callback function untuk aksi logout (disediakan untuk kebutuhan masa depan)
 */
@Composable
fun HomeScreen(
    onLogout: () -> Unit, // Aksi logout tetap ada jika dibutuhkan di tempat lain
    onNavigateToProfilDesa: () -> Unit,
    onNavigateToBumdes: () -> Unit,
    onNavigateToGaleri: () -> Unit,
    onNavigateToUnggahDokumen: () -> Unit
) {


    // === DEFINISI WARNA ===
    val primaryColor = Color(0xFF00BFA5) // Warna hijau toska utama aplikasi
    val backgroundColor = Color(0xFF00BFA5) // Warna latar belakang sedikit hijau

    // === CONTAINER UTAMA DENGAN GRADIENT BACKGROUND ===
    Box(
        modifier = Modifier
            .fillMaxSize() // Mengisi seluruh ukuran layar
            .background(
                // Gradient vertikal dari hijau toska ke background color
                brush = Brush.verticalGradient(
                    colors = listOf(primaryColor, backgroundColor),
                    startY = 0f, // Mulai dari atas
                    endY = 1000f // Sesuaikan nilai ini untuk panjang gradient
                )
            )
    ) {
        // === SCROLLABLE CONTENT MENGGUNAKAN LAZY COLUMN ===
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp) // Padding untuk seluruh content
        ) {
            // === BAGIAN HEADER ===
            item {
                HomeHeader() // Composable untuk header dengan logo dan nama desa
                Spacer(modifier = Modifier.height(24.dp)) // Spacing setelah header
            }

            // === BAGIAN MENU GRID ===
            item {
                MenuGridSection(
                    onNavigateToProfilDesa = onNavigateToProfilDesa,
                    onNavigateToBumdes = onNavigateToBumdes,
                    onNavigateToGaleri = onNavigateToGaleri,
                    onNavigateToUnggahDokumen = onNavigateToUnggahDokumen
                ) // Composable untuk grid menu layanan
                Spacer(modifier = Modifier.height(24.dp)) // Spacing setelah menu
            }

            // === BAGIAN INFO GRAFIS ===
            item {
                InfoGrafisSection() // Composable untuk menampilkan info grafis
                Spacer(modifier = Modifier.height(24.dp)) // Spacing setelah info grafis
            }

            // === BAGIAN BERITA ===
            item {
                BeritaSection() // Composable untuk menampilkan berita terkini
            }
        }
    }
}

/**
 * Composable untuk menampilkan header halaman home
 * Berisi logo desa dan informasi nama desa dengan kabupaten
 */
@Composable
fun HomeHeader() {
    // Row untuk layout horizontal logo dan text
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically // Align vertikal ke tengah
    ) {
        // === LOGO DESA ===
        Image(
            painter = painterResource(id = R.drawable.lambang_kabupaten_subang), // Resource logo
            contentDescription = "Logo Desa", // Accessibility description
            modifier = Modifier
                .size(48.dp) // Ukuran logo 48x48 dp
                .clip(CircleShape) // Bentuk circular
        )

        Spacer(modifier = Modifier.width(12.dp)) // Spacing horizontal antara logo dan text

        // === INFORMASI DESA ===
        Column {
            // Nama desa dengan styling bold dan warna putih
            Text(
                text = "Desa Sukamandi",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            // Nama kabupaten dengan warna putih transparan
            Text(
                text = "Kab. Subang",
                color = Color.White.copy(alpha = 0.8f), // Transparansi 80%
                fontSize = 14.sp
            )
        }
    }
}

/**
 * Composable untuk menampilkan grid menu layanan desa
 * Menu ditampilkan dalam Card dengan arrangement grid custom (4 kolom per baris)
 */
@Composable
fun MenuGridSection(
    onNavigateToProfilDesa: () -> Unit,
    onNavigateToBumdes: () -> Unit,
    onNavigateToGaleri: () -> Unit,
    onNavigateToUnggahDokumen: () -> Unit
    ) {
    // === DEFINISI MENU ITEMS ===
    // List menu dengan title dan icon yang akan ditampilkan
    val menuItems = listOf(
        MenuItem("Profil Desa", Icons.Default.AccountBalance),
        MenuItem("Potensi Desa", Icons.Default.Landscape),
        MenuItem("Program Desa", Icons.Default.Event),
        MenuItem("Pemerintah Desa", Icons.Default.Groups),
        MenuItem("Unggah Dokumen", Icons.Default.CloudUpload),
        MenuItem("BUMDES", Icons.Default.Store),
        MenuItem("Galeri", Icons.Default.PhotoLibrary)
    )

    // === CARD CONTAINER UNTUK MENU ===
    Card(
        shape = RoundedCornerShape(16.dp), // Rounded corner dengan radius 16dp
        elevation = CardDefaults.cardElevation(4.dp), // Shadow elevation 4dp
        colors = CardDefaults.cardColors(containerColor = Color.White) // Background putih
    ) {
        Column(
            modifier = Modifier.padding(16.dp) // Padding internal card
        ) {
            // === ARRANGEMENT MENU DALAM GRID CUSTOM ===
            // Membagi item menjadi beberapa baris, setiap baris berisi maksimal 4 item
            menuItems.chunked(4).forEach { rowItems ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp), // Padding vertikal untuk setiap baris
                    horizontalArrangement = Arrangement.spacedBy(16.dp) // Spacing antar item
                ) {
                    // === RENDER SETIAP MENU ITEM ===
                    rowItems.forEach { item ->
                        Box(modifier = Modifier.weight(1f)) { // Weight 1f untuk distribusi equal
                            MenuIconItem(
                                menuItem = item,
                                onClick = {
                                    // UBAH LOGIKA onClick MENJADI SEPERTI INI
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

                    // === FILL EMPTY SPACES ===
                    // Jika item dalam satu baris kurang dari 4, tambahkan Spacer kosong
                    // agar tata letak tetap rapi dan konsisten
                    repeat(4 - rowItems.size) {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        }
    }
}

/**
 * Composable untuk menampilkan individual menu item
 * Berisi icon dalam circle background dan text label di bawahnya
 *
 * @param menuItem Data class berisi title dan icon untuk menu
 * @param onClick Callback function yang dipanggil saat item diklik
 */
@Composable
fun MenuIconItem(menuItem: MenuItem, onClick: () -> Unit) {
    // Column untuk layout vertikal icon dan text
    Column(
        horizontalAlignment = Alignment.CenterHorizontally, // Center alignment horizontal
        modifier = Modifier.clickable(onClick = onClick) // Membuat seluruh area clickable
    ) {
        // === ICON CONTAINER ===
        Box(
            modifier = Modifier
                .size(56.dp) // Ukuran container 56x56 dp
                .clip(CircleShape) // Bentuk circular
                .background(Color(0xFFF0F0F0)), // Background abu-abu terang
            contentAlignment = Alignment.Center // Center alignment untuk icon
        ) {
            // Icon dengan styling dan ukuran custom
            Icon(
                imageVector = menuItem.icon, // Vector icon dari MenuItem
                contentDescription = menuItem.title, // Accessibility description
                tint = Color.Gray, // Warna icon abu-abu
                modifier = Modifier.size(28.dp) // Ukuran icon 28x28 dp
            )
        }

        Spacer(modifier = Modifier.height(8.dp)) // Spacing antara icon dan text

        // === TEXT LABEL ===
        Text(
            text = menuItem.title, // Text dari MenuItem
            fontSize = 12.sp, // Ukuran font kecil
            textAlign = TextAlign.Center, // Center alignment
            lineHeight = 14.sp // Line height untuk readability
        )
    }
}

/**
 * Composable untuk menampilkan section Info Grafis
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
        // === JUDUL SECTION ===
        Text(
            text = "Info Grafis",
            style = MaterialTheme.typography.titleLarge, // Typography style large
            fontWeight = FontWeight.Bold,
            color = Color.White // Warna putih untuk kontras dengan background
        )

        Spacer(modifier = Modifier.height(12.dp)) // Spacing antara judul dan card

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 8.dp), // Sedikit padding jika gambar terlalu ke tepi
            pageSpacing = 16.dp // Jarak antar gambar saat digeser

        // === CARD INFOGRAFIS ===
        ) { page ->
            // Setiap item di dalam Pager adalah sebuah Card
            Card(
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(4.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = infografisItems[page]), // Ambil gambar sesuai halaman
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

        // 4. Indikator halaman (titik-titik) di bawah carousel
        Row(
            Modifier
                .height(20.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            // Ulangi sebanyak jumlah gambar
            repeat(pagerState.pageCount) { iteration ->
                // Tentukan warna titik berdasarkan halaman saat ini
                val color =
                    if (pagerState.currentPage == iteration) primaryColor else Color.LightGray
                // Tentukan ukuran titik, yang aktif sedikit lebih besar
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
 * Composable untuk menampilkan section Berita
 * Berisi judul section dan gambar berita dalam Card yang clickable
 */
@Composable
fun BeritaSection() {
    Column(modifier = Modifier.fillMaxWidth()) {
        // === JUDUL SECTION ===
        Text(
            text = "Berita",
            style = MaterialTheme.typography.titleLarge, // Typography style large
            fontWeight = FontWeight.Bold,
            color = Color.White // Warna putih untuk kontras
        )

        Spacer(modifier = Modifier.height(12.dp)) // Spacing antara judul dan card

        // === CARD BERITA ===
        Card(
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(4.dp),
            modifier = Modifier.clickable { /* TODO: Handle berita click */ }
        ) {
            // 1. Bungkus Gambar dan Teks dalam sebuah Box
            // Box memungkinkan elemen untuk ditumpuk di atas satu sama lain.
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
            ) {
                // 2. Gambar sebagai latar belakang, mengisi seluruh Box
                Image(
                    painter = painterResource(id = R.drawable.berita_subang),
                    contentDescription = "Berita Kecelakaan",
                    modifier = Modifier.fillMaxSize(), // Mengisi seluruh ukuran Box
                    contentScale = ContentScale.Crop
                )

                // 3. Gradien overlay untuk meningkatkan keterbacaan teks
                // Diletakkan di bagian bawah Box, di atas gambar.
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.8f)),
                                startY = 300f // Mulai gradien dari tengah ke bawah
                            )
                        )
                )

                // 4. Teks Judul Berita
                // Diletakkan di bagian bawah Box, di atas gradien.
                Text(
                    text = "Kecelakaan Maut di Jalur Pantura Subang, 2 Orang Meninggal Dunia",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier
                        .align(Alignment.BottomStart) // Posisi di kiri bawah
                        .padding(16.dp) // Beri jarak dari tepi
                )
            }
        }
    }
}