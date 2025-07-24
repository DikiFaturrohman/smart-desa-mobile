package com.example.smart_desa.presentation.ui.screen.auth.register

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smart_desa.R

/**
 * Composable utama untuk halaman registrasi/pendaftaran akun baru
 * Menggunakan Scaffold untuk layout yang konsisten dengan TopAppBar
 * Form menggunakan LazyColumn untuk handling content yang panjang dan scrollable
 *
 * @param onRegisterSuccess Callback yang dipanggil ketika registrasi berhasil
 * @param onBackPress Callback untuk navigasi kembali ke halaman sebelumnya
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    onRegisterSuccess: () -> Unit,
    onBackPress: () -> Unit
) {
    // === STATE MANAGEMENT UNTUK FORM FIELDS ===
    // Menggunakan remember dan mutableStateOf untuk local state management
    var nik by remember { mutableStateOf("") } // State untuk input NIK
    var namaLengkap by remember { mutableStateOf("") } // State untuk input nama lengkap
    var email by remember { mutableStateOf("") } // State untuk input email
    var password by remember { mutableStateOf("") } // State untuk input password
    var konfirmasiPassword by remember { mutableStateOf("") } // State untuk konfirmasi password
    var nomorTelepon by remember { mutableStateOf("") } // State untuk input nomor telepon
    var alamatLengkap by remember { mutableStateOf("") } // State untuk input alamat
    var tanggalLahir by remember { mutableStateOf("") } // State untuk input tanggal lahir
    var selectedDesa by remember { mutableStateOf("") } // State untuk dropdown pilihan desa

    // === CONTEXT DAN STYLING ===
    val context = LocalContext.current // Context untuk Toast notification
    val primaryColor = Color(0xFF00BFA5) // Warna primary hijau toska aplikasi

    // === SCAFFOLD SEBAGAI CONTAINER UTAMA ===
    // Menggunakan Scaffold untuk layout yang konsisten dengan TopAppBar
    Scaffold(
        // === TOP APP BAR ===
        topBar = {
            TopAppBar(
                title = { }, // Empty title, hanya menggunakan navigation icon
                navigationIcon = {
                    // Tombol back dengan arrow icon
                    IconButton(onClick = onBackPress) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Kembali", // Accessibility description
                            tint = Color.White // Warna putih untuk kontras dengan background
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent // Transparent agar terlihat gradient background
                )
            )
        },
        // Container color scaffold disesuaikan dengan primary color
        containerColor = primaryColor
    ) { paddingValues -> // PaddingValues dari Scaffold untuk menghindari overlap dengan TopAppBar

        // === MAIN CONTENT COLUMN ===
        Column(
            modifier = Modifier.fillMaxSize() // Mengisi seluruh ukuran layar
        ) {
            // === BAGIAN ATAS - HEADER DENGAN LOGO ===
            // Box container untuk bagian header dengan gradient background
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.7f) // Mengambil 70% dari ruang vertikal tersedia
                    .background(
                        // Gradient vertikal dari hijau toska ke hijau toska gelap
                        brush = Brush.verticalGradient(
                            colors = listOf(primaryColor, Color(0xFF00897B))
                        )
                    ),
                contentAlignment = Alignment.Center // Center alignment untuk logo
            ) {
                // Spacer untuk mendorong logo ke bawah sesuai tinggi TopAppBar
                // agar posisi logo tetap konsisten dengan desain
                Spacer(modifier = Modifier.height(paddingValues.calculateTopPadding()))

                // === LOGO KABUPATEN ===
                Image(
                    painter = painterResource(id = R.drawable.lambang_kabupaten_subang),
                    contentDescription = "Logo Desa", // Accessibility description
                    modifier = Modifier
                        .size(110.dp) // Ukuran logo 110x110 dp
                        .shadow(8.dp, CircleShape) // Shadow dengan radius 8dp dan bentuk circle
                        .clip(CircleShape) // Clipping gambar menjadi lingkaran
                        .background(Color.White) // Background putih untuk logo
                        .padding(12.dp), // Padding internal logo
                    contentScale = ContentScale.Fit // Scaling agar gambar fit dalam container
                )
            }

            // === BAGIAN BAWAH - FORM REGISTRASI ===
            // LazyColumn untuk form yang panjang dan scrollable
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(2.3f) // Mengambil 230% dari ruang vertikal tersisa
                    .background(MaterialTheme.colorScheme.surface), // Background sesuai theme
                // Content padding untuk spacing dan menghindari overlap dengan TopAppBar
                contentPadding = PaddingValues(
                    top = 24.dp, // Padding atas
                    bottom = 24.dp, // Padding bawah
                    start = 24.dp, // Padding kiri
                    end = 24.dp // Padding kanan
                ),
                horizontalAlignment = Alignment.CenterHorizontally // Center alignment horizontal
            ) {
                // === FORM INPUT FIELDS ===
                // Setiap item menggunakan FormInputField composable untuk konsistensi

                // Input field untuk NIK
                item {
                    FormInputField(
                        label = "NIK :",
                        value = nik,
                        placeholder = "NIK",
                        onValueChange = { nik = it }
                    )
                }

                // Input field untuk Nama Lengkap
                item {
                    FormInputField(
                        label = "Nama Lengkap :",
                        value = namaLengkap,
                        placeholder = "Nama Lengkap",
                        onValueChange = { namaLengkap = it }
                    )
                }

                // Input field untuk Email
                item {
                    FormInputField(
                        label = "Email :",
                        value = email,
                        placeholder = "Email",
                        onValueChange = { email = it }
                    )
                }

                // Input field untuk Password (dengan password transformation)
                item {
                    FormInputField(
                        label = "Password :",
                        value = password,
                        placeholder = "Password",
                        onValueChange = { password = it },
                        isPassword = true // Flag untuk password transformation
                    )
                }

                // Input field untuk Konfirmasi Password
                item {
                    FormInputField(
                        label = "Konfirmasi Password :",
                        value = konfirmasiPassword,
                        placeholder = "Konfirmasi Password",
                        onValueChange = { konfirmasiPassword = it },
                        isPassword = true // Flag untuk password transformation
                    )
                }

                // Input field untuk Nomor Telepon
                item {
                    FormInputField(
                        label = "Nomor Telepon :",
                        value = nomorTelepon,
                        placeholder = "Nomor Telepon",
                        onValueChange = { nomorTelepon = it }
                    )
                }

                // Input field untuk Alamat Lengkap
                item {
                    FormInputField(
                        label = "Alamat Lengkap :",
                        value = alamatLengkap,
                        placeholder = "Alamat Lengkap",
                        onValueChange = { alamatLengkap = it }
                    )
                }

                // Input field untuk Tanggal Lahir
                item {
                    FormInputField(
                        label = "Tanggal Lahir :",
                        value = tanggalLahir,
                        placeholder = "mm/dd/yy",
                        onValueChange = { tanggalLahir = it }
                    )
                }

                // Dropdown field untuk pilihan desa
                item {
                    DesaDropdownField(
                        selectedText = selectedDesa,
                        onDesaSelected = { selectedDesa = it }
                    )
                }

                // === SUBMIT BUTTON ===
                item {
                    Spacer(modifier = Modifier.height(24.dp)) // Spacing sebelum button

                    // Tombol registrasi utama
                    Button(
                        onClick = {
                            // Aksi ketika tombol diklik
                            // Menampilkan toast sukses dan panggil callback
                            Toast.makeText(context, "Registrasi Berhasil, Silakan Login", Toast.LENGTH_SHORT).show()
                            onRegisterSuccess() // Callback ke parent untuk navigasi
                        },
                        modifier = Modifier
                            .fillMaxWidth() // Lebar penuh
                            .height(50.dp), // Tinggi 50dp
                        shape = RoundedCornerShape(12.dp), // Rounded corner 12dp
                        colors = ButtonDefaults.buttonColors(containerColor = primaryColor) // Warna primary
                    ) {
                        Text("Daftar", fontSize = 16.sp) // Text dengan ukuran font 16sp
                    }

                    Spacer(modifier = Modifier.height(16.dp)) // Spacing setelah button
                }
            }
        }
    }
}

// === COMPOSABLE HELPER FUNCTIONS ===

/**
 * Composable terpisah untuk input field agar tidak berulang
 * Menyediakan styling yang konsisten untuk semua form field
 *
 * @param label Label yang ditampilkan di atas field
 * @param value Nilai current dari field
 * @param placeholder Placeholder text untuk field
 * @param onValueChange Callback ketika nilai field berubah
 * @param isPassword Flag untuk menentukan apakah field adalah password (default: false)
 */
@Composable
private fun FormInputField(
    label: String,
    value: String,
    placeholder: String,
    onValueChange: (String) -> Unit,
    isPassword: Boolean = false
) {
    // Column untuk layout vertikal label dan TextField
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start // Alignment kiri untuk label
    ) {
        // === LABEL TEXT ===
        Text(
            label,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.SemiBold
        )

        // === TEXT FIELD ===
        TextField(
            value = value, // Nilai dari state
            onValueChange = onValueChange, // Callback untuk update state
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(placeholder, color = Color.LightGray) // Placeholder dengan warna abu-abu terang
            },
            // Visual transformation untuk password field
            visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent, // Background transparan saat focus
                unfocusedContainerColor = Color.Transparent, // Background transparan saat tidak focus
                focusedIndicatorColor = Color(0xFF00BFA5), // Warna garis bawah saat focus
                cursorColor = Color(0xFF00BFA5) // Warna cursor
            ),
            singleLine = true // Single line input
        )

        // Spacing setelah setiap field
        Spacer(modifier = Modifier.height(16.dp))
    }
}

/**
 * Composable terpisah untuk dropdown field pilihan desa
 * Menggunakan ExposedDropdownMenu untuk user experience yang baik
 *
 * @param selectedText Text yang currently selected
 * @param onDesaSelected Callback ketika desa dipilih
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DesaDropdownField(
    selectedText: String,
    onDesaSelected: (String) -> Unit
) {
    // === DROPDOWN OPTIONS ===
    // List pilihan desa yang tersedia
    val desaOptions = listOf(
        "Desa Sukamandi",
        "Desa Sagalaherang Kaler",
        "Desa Sagalaherang Kidul"
    )

    // State untuk mengontrol expanded/collapsed dropdown
    var expanded by remember { mutableStateOf(false) }

    // Column untuk layout vertikal label dan dropdown
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        // === LABEL TEXT ===
        Text(
            "Pilih Desa :",
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.SemiBold
        )

        // === EXPOSED DROPDOWN MENU ===
        ExposedDropdownMenuBox(
            expanded = expanded, // State expanded
            onExpandedChange = { expanded = !expanded } // Toggle expanded state
        ) {
            // === DROPDOWN TRIGGER FIELD ===
            TextField(
                value = selectedText, // Selected value
                onValueChange = {}, // Empty karena read-only
                readOnly = true, // Read-only field, hanya bisa diklik untuk membuka dropdown
                placeholder = {
                    Text("Nama Desa", color = Color.LightGray) // Placeholder
                },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) // Arrow icon
                },
                modifier = Modifier
                    .menuAnchor() // Anchor untuk dropdown menu
                    .fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent, // Background transparan
                    unfocusedContainerColor = Color.Transparent, // Background transparan
                    focusedIndicatorColor = Color(0xFF00BFA5) // Warna garis bawah
                )
            )

            // === DROPDOWN MENU ITEMS ===
            ExposedDropdownMenu(
                expanded = expanded, // State expanded
                onDismissRequest = { expanded = false } // Close dropdown ketika klik di luar
            ) {
                // Iterasi semua pilihan desa
                desaOptions.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(option) }, // Text pilihan
                        onClick = {
                            onDesaSelected(option) // Callback dengan pilihan yang dipilih
                            expanded = false // Close dropdown setelah pilih
                        }
                    )
                }
            }
        }
    }
}