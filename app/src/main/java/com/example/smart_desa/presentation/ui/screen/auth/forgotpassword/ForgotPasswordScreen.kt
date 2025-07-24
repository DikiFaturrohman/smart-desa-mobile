package com.example.smart_desa.presentation.ui.screen.auth.forgotpassword

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Composable untuk halaman Lupa Kata Sandi.
 * Pengguna memasukkan nomor telepon untuk menerima kode verifikasi.
 *
 * @param onBackPress Callback untuk navigasi kembali ke halaman sebelumnya.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForgotPasswordScreen(
    onBackPress: () -> Unit
) {
    // State untuk menyimpan input nomor telepon dari pengguna
    var nomorTelepon by remember { mutableStateOf("") }
    // Konteks lokal untuk menampilkan Toast
    val context = LocalContext.current
    // Warna utama aplikasi
    val primaryColor = Color(0xFF00BFA5)

    // Scaffold menyediakan struktur dasar Material Design (termasuk TopAppBar)
    Scaffold(
        topBar = {
            // TopAppBar transparan dengan tombol kembali
            TopAppBar(
                title = { }, // Judul kosong
                navigationIcon = {
                    IconButton(onClick = onBackPress) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Kembali",
                            tint = Color.White // Ikon berwarna putih agar kontras
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent // Transparan agar gradien terlihat
                )
            )
        },
        containerColor = primaryColor // Warna latar belakang utama Scaffold
    ) { paddingValues ->

        // Container utama yang mengisi seluruh layar
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // === BAGIAN ATAS - HEADER ===
            // Box untuk header dengan latar belakang gradien
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f) // Mengambil 1/3 bagian atas layar
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(primaryColor, Color(0xFF00897B))
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {
                // Spacer untuk memastikan konten di bawah TopAppBar
                Spacer(modifier = Modifier.height(paddingValues.calculateTopPadding()))
                // Di sini Anda bisa menambahkan logo jika diinginkan, seperti di halaman login
            }

            // === BAGIAN BAWAH - FORM ===
            // Column untuk form dengan sudut atas yang membulat
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(2f) // Mengambil 2/3 bagian bawah layar
                    .clip(RoundedCornerShape(topStart = 0.dp, topEnd = 0.dp))
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(horizontal = 24.dp, vertical = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // === HEADER TEXT ===
                Text(
                    text = "Lupa Kata Sandi?",
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Masukkan nomor telepon yang terdaftar untuk menerima kode verifikasi.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(32.dp))

                // === INPUT FIELD NOMOR TELEPON ===
                Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start) {
                    Text("Nomor Telepon :", style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.SemiBold)
                    TextField(
                        value = nomorTelepon,
                        onValueChange = { nomorTelepon = it },
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = { Text("08xxxxxxxxxx", color = Color.LightGray) },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            focusedIndicatorColor = primaryColor,
                            cursorColor = primaryColor
                        ),
                        singleLine = true
                    )
                }
                Spacer(modifier = Modifier.height(32.dp))

                // === TOMBOL KIRIM KODE ===
                Button(
                    onClick = {
                        // Aksi saat ini hanya menampilkan Toast
                        Toast.makeText(context, "Fungsi ini belum tersedia", Toast.LENGTH_SHORT).show()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = primaryColor)
                ) {
                    Text("Kirim Kode", fontSize = 16.sp)
                }
                // Spacer untuk mendorong konten ke atas
                Spacer(modifier = Modifier.weight(1f))
            }
        }
    }
}