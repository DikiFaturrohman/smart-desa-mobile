package com.example.smart_desa.presentation.ui.screen.auth.login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.smart_desa.R

/**
 * Composable function untuk layar login aplikasi Smart Desa
 *
 * @param onLoginSuccess Callback yang dipanggil ketika login berhasil
 * @param onNavigateToRegister Callback untuk navigasi ke halaman registrasi
 * @param loginViewModel ViewModel untuk mengelola state dan business logic login
 */

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    onNavigateToRegister: () -> Unit,
    onNavigateToForgotPassword: () -> Unit,
    loginViewModel: LoginViewModel = viewModel()
) {
    // Mengobserve state dari ViewModel menggunakan collectAsState
    val state by loginViewModel.state.collectAsState()
    // Mendapatkan context untuk menampilkan Toast
    val context = LocalContext.current
    // Definisi warna utama aplikasi - hijau toska
    val primaryColor = Color(0xFF00BFA5)


    // Container utama yang mengisi seluruh layar
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // === BAGIAN ATAS - HEADER DENGAN LOGO ===
            // Box untuk bagian header dengan gradient background
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f) // Mengambil 1/3 bagian atas layar
                    .background(  // Gradient vertikal dari hijau toska ke hijau toska gelap
                        brush = Brush.verticalGradient(
                            colors = listOf(primaryColor, Color(0xFF00897B))
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {
                // Logo kabupaten dengan styling circular dan shadow
                Image(
                    painter = painterResource(id = R.drawable.lambang_kabupaten_subang),
                    contentDescription = "Logo Desa",
                    modifier = Modifier
                        .size(120.dp)   // Ukuran logo 120dp x 120dp
                        .shadow(8.dp, CircleShape)  // Shadow dengan radius 8dp
                        .clip(CircleShape)  // Memotong gambar menjadi lingkaran
                        .background(Color.White)    // Background putih untuk logo
                        .padding(12.dp),    // Padding internal logo
                    contentScale = ContentScale.Fit     // Scaling gambar agar fit
                )
            }

            // === BAGIAN BAWAH - FORM LOGIN ===
            // Column untuk form login dengan rounded corner di bagian atas
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(2f) // Mengambil 2/3 bagian bawah layar
                    .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)) // Rounded corner atas
                    .background(MaterialTheme.colorScheme.surface) // Background sesuai theme
                    .padding(horizontal = 24.dp, vertical = 32.dp), // Padding horizontal dan vertikal
                horizontalAlignment = Alignment.CenterHorizontally // Alignment tengah
            ) {
                // === HEADER TEXT ===
                // Judul utama halaman login
                Text(
                    text = "Selamat Datang,",
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Bold
                )
                // Subtitle dengan informasi tambahan
                Text(
                    text = "Daftar sekarang untuk mendapatkan akses penuh layanan kami.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(24.dp))

                // === INPUT FIELD NIK ===
                // Column untuk input NIK dengan label
                Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start) {
                    // Label untuk field NIK
                    Text("NIK :", style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.SemiBold)
                    // TextField untuk input NIK
                    TextField(
                        value = state.nik, // Value dari state ViewModel
                        onValueChange = loginViewModel::onNikChange, // Callback ke ViewModel
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = { Text("NIK", color = Color.LightGray) }, // Placeholder text
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent, // Background transparan saat focus
                            unfocusedContainerColor = Color.Transparent, // Background transparan saat tidak focus
                            focusedIndicatorColor = primaryColor, // Warna garis bawah saat focus
                            cursorColor = primaryColor // Warna cursor
                        ),
                        singleLine = true, // Single line input
                        enabled = !state.isLoading // Disable saat loading
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))

                // === INPUT FIELD PASSWORD ===
                // State untuk mengontrol visibilitas password
                var passwordVisible by remember { mutableStateOf(false) }
                // Column untuk input password dengan label
                Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start) {
                    // Label untuk field password
                    Text("Password :", style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.SemiBold)
                    // TextField untuk input password
                    TextField(
                        value = state.password, // Value dari state ViewModel
                        onValueChange = loginViewModel::onPasswordChange, // Callback ke ViewModel
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = { Text("Password", color = Color.LightGray) }, // Placeholder text
                        // Mengubah visualTransformation berdasarkan state passwordVisible
                        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password), // Keyboard type password
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent, // Background transparan saat focus
                            unfocusedContainerColor = Color.Transparent, // Background transparan saat tidak focus
                            focusedIndicatorColor = primaryColor, // Warna garis bawah saat focus
                            cursorColor = primaryColor // Warna cursor
                        ),
                        singleLine = true, // Single line input
                        enabled = !state.isLoading, // Disable saat loading
                        // Menambahkan ikon di akhir TextField untuk toggle visibilitas
                        trailingIcon = {
                            val image = if (passwordVisible)
                                Icons.Filled.VisibilityOff
                            else Icons.Filled.Visibility

                            val description = if (passwordVisible) "Sembunyikan password" else "Tampilkan password"

                            // IconButton untuk mengubah state passwordVisible saat diklik
                            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                Icon(imageVector = image, contentDescription = description)
                            }
                        }
                    )
                }
                // === FORGOT PASSWORD BUTTON ===
                // Box untuk menampung tombol lupa password dengan alignment kanan
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
                    TextButton(onClick = { onNavigateToForgotPassword() }) {
                        Text("Lupa Kata Sandi?", color = Color.Gray)
                    }
                }
                Spacer(modifier = Modifier.height(16.dp)) // Spacer 16dp

                // === LOGIN BUTTON ===
                // Tombol utama untuk melakukan login
                Button(
                    onClick = {
                        // Memanggil fungsi login dari ViewModel dengan callback
                        loginViewModel.login(
                            onLoginSuccess = onLoginSuccess, // Callback jika berhasil
                            onLoginFailed = {
                                // Callback jika gagal - menampilkan Toast error
                                Toast.makeText(context, "NIK atau Password Salah", Toast.LENGTH_SHORT).show()
                            }
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth() // Lebar penuh
                        .height(50.dp), // Tinggi 50dp
                    shape = RoundedCornerShape(12.dp), // Rounded corner 12dp
                    colors = ButtonDefaults.buttonColors(containerColor = primaryColor), // Warna background
                    enabled = !state.isLoading // Disable saat loading
                ) {
                    Text("Masuk", fontSize = 16.sp) // Text tombol dengan ukuran font 16sp
                }

                Spacer(modifier = Modifier.weight(1f))

                // === REGISTER LINK ===
                // Link navigasi ke halaman registrasi dengan styling khusus
                ClickableText(
                    text = buildAnnotatedString {
                        // Bagian text normal dengan warna abu-abu
                        withStyle(style = SpanStyle(color = Color.Gray)) {
                            append("Belum punya akun? ")
                        }
                        // Bagian text "Daftar" dengan warna primary dan bold
                        withStyle(style = SpanStyle(color = primaryColor, fontWeight = FontWeight.Bold)) {
                            append("Daftar")
                        }
                    },
                    onClick = { onNavigateToRegister() } // Callback navigasi ke register
                )
            }
        }

        // === LOADING INDICATOR ===
        // CircularProgressIndicator yang ditampilkan saat loading
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}