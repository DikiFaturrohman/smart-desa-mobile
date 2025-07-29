package com.example.smart_desa.presentation.ui.screen.berita

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smart_desa.R
import com.example.smart_desa.presentation.ui.screen.home.BeritaItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BeritaDetailScreen(
    beritaId: Int,
    onBackPress: () -> Unit
) {
    // Di aplikasi nyata, Anda akan menggunakan beritaId untuk mengambil data dari ViewModel.
    // Untuk saat ini, kita gunakan data dummy.
    val berita = BeritaItem(
        id = beritaId,
        title = "Kecelakaan Maut di Jalur Pantura Subang, 2 Orang Meninggal Dunia",
        imageRes = R.drawable.berita_subang
    )
    var commentText by remember { mutableStateOf("") }
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detail Berita") },
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            // Judul Berita
            Text(
                text = berita.title,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Diposting pada 29 Juli 2025",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Gambar Berita
            Image(
                painter = painterResource(id = berita.imageRes),
                contentDescription = berita.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Isi Berita (Dummy)
            Text(
                text = "Ini adalah isi lengkap dari berita kecelakaan yang terjadi di Jalur Pantura Subang. Teks ini hanyalah contoh dan akan digantikan dengan konten berita sebenarnya dari API atau database. Kecelakaan ini melibatkan beberapa kendaraan dan menyebabkan kemacetan panjang.",
                style = MaterialTheme.typography.bodyLarge,
                lineHeight = 24.sp
            )
            Spacer(modifier = Modifier.height(24.dp))

            // Garis Pemisah
            Divider()
            Spacer(modifier = Modifier.height(16.dp))

            // Bagian Komentar
            Text(
                text = "Komentar",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = commentText,
                onValueChange = { commentText = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Tulis komentar Anda...") },
                label = { Text("Komentar") }
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = {
                    Toast.makeText(context, "Komentar terkirim!", Toast.LENGTH_SHORT).show()
                    commentText = ""
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00BFA5))
            ) {
                Text("Kirim")
            }
        }
    }
}