package com.example.smart_desa.presentation.ui.screen.galeri

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.PlayCircleFilled
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.smart_desa.R

data class VideoItem(val title: String, val uploader: String, val thumbnailRes: Int)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VideoScreen(onBackPress: () -> Unit) {
    val daftarVideo = listOf(
        VideoItem("Peresmian Jalan Desa oleh Bupati", "Pemdes Sukamandi", R.drawable.berita_subang),
        VideoItem("Pelatihan UMKM Ibu-Ibu PKK", "BUMDES Jaya", R.drawable.infografis),
        VideoItem("Pentas Seni dan Budaya Desa", "Karang Taruna", R.drawable.infograf)
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Galeri Video") },
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
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(daftarVideo) { video ->
                VideoCard(video = video)
            }
        }
    }
}

@Composable
fun VideoCard(video: VideoItem) {
    Card(
        elevation = CardDefaults.cardElevation(4.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = video.thumbnailRes),
                    contentDescription = video.title,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                Icon(
                    imageVector = Icons.Default.PlayCircleFilled,
                    contentDescription = "Play Video",
                    modifier = Modifier.size(64.dp),
                    tint = Color.White.copy(alpha = 0.8f)
                )
            }
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = video.title, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "Diupload oleh: ${video.uploader}", style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
            }
        }
    }
}