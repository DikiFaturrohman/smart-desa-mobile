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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    onRegisterSuccess: () -> Unit,
    onBackPress: () -> Unit
) {
    // State untuk semua field
    var nik by remember { mutableStateOf("") }
    var namaLengkap by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var konfirmasiPassword by remember { mutableStateOf("") }
    var nomorTelepon by remember { mutableStateOf("") }
    var alamatLengkap by remember { mutableStateOf("") }
    var tanggalLahir by remember { mutableStateOf("") }
    var selectedDesa by remember { mutableStateOf("") }

    val context = LocalContext.current
    val primaryColor = Color(0xFF00BFA5)

    // PERUBAHAN UTAMA: Gunakan Scaffold sebagai dasar
    Scaffold(
        topBar = {
            TopAppBar(
                title = { },
                navigationIcon = {
                    IconButton(onClick = onBackPress) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Kembali",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                )
            )
        },
        // Atur warna container Scaffold agar cocok jika ada kebocoran visual
        containerColor = primaryColor
    ) { paddingValues -> // paddingValues dari Scaffold
        Column(
            // Column ini tidak lagi menggunakan padding, agar bisa memenuhi seluruh layar
            modifier = Modifier.fillMaxSize()
        ) {
            // Bagian Atas - Latar Hijau dan Logo
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.7f)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(primaryColor, Color(0xFF00897B))
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {
                // Spacer ini untuk mendorong Logo ke bawah sesuai tinggi TopAppBar
                // agar posisi logo tetap sama seperti desain
                Spacer(modifier = Modifier.height(paddingValues.calculateTopPadding()))
                Image(
                    painter = painterResource(id = R.drawable.lambang_kabupaten_subang),
                    contentDescription = "Logo Desa",
                    modifier = Modifier
                        .size(110.dp)
                        .shadow(8.dp, CircleShape)
                        .clip(CircleShape)
                        .background(Color.White)
                        .padding(12.dp),
                    contentScale = ContentScale.Fit
                )
            }
            // Bagian Bawah - Kartu Form Putih
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(2.3f)
                    .background(MaterialTheme.colorScheme.surface),
                // Padding dari Scaffold diterapkan di sini agar item pertama tidak tertutup TopAppBar
                contentPadding = PaddingValues(
                    top = 24.dp, // Padding atas ditambah padding scaffold
                    bottom = 24.dp,
                    start = 24.dp,
                    end = 24.dp
                ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item { FormInputField(label = "NIK :", value = nik, placeholder = "NIK", onValueChange = { nik = it }) }
                item { FormInputField(label = "Nama Lengkap :", value = namaLengkap, placeholder = "Nama Lengkap", onValueChange = { namaLengkap = it }) }
                item { FormInputField(label = "Email :", value = email, placeholder = "Email", onValueChange = { email = it }) }
                item { FormInputField(label = "Password :", value = password, placeholder = "Password", onValueChange = { password = it }, isPassword = true) }
                item { FormInputField(label = "Konfirmasi Password :", value = konfirmasiPassword, placeholder = "Konfirmasi Password", onValueChange = { konfirmasiPassword = it }, isPassword = true) }
                item { FormInputField(label = "Nomor Telepon :", value = nomorTelepon, placeholder = "Nomor Telepon", onValueChange = { nomorTelepon = it }) }
                item { FormInputField(label = "Alamat Lengkap :", value = alamatLengkap, placeholder = "Alamat Lengkap", onValueChange = { alamatLengkap = it }) }
                item { FormInputField(label = "Tanggal Lahir :", value = tanggalLahir, placeholder = "mm/dd/yy", onValueChange = { tanggalLahir = it }) }
                item { DesaDropdownField(selectedText = selectedDesa, onDesaSelected = { selectedDesa = it }) }
                item {
                    Spacer(modifier = Modifier.height(24.dp))
                    Button(
                        onClick = {
                            Toast.makeText(context, "Registrasi Berhasil, Silakan Login", Toast.LENGTH_SHORT).show()
                            onRegisterSuccess()
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = primaryColor)
                    ) {
                        Text("Daftar", fontSize = 16.sp)
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}

// ... (Composable FormInputField dan DesaDropdownField tetap sama, tidak perlu diubah)
// ...

    // Composable terpisah untuk input field agar tidak berulang
    @Composable
    private fun FormInputField(
        label: String,
        value: String,
        placeholder: String,
        onValueChange: (String) -> Unit,
        isPassword: Boolean = false
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            Text(label, style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.SemiBold)
            TextField(
                value = value,
                onValueChange = onValueChange,
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text(placeholder, color = Color.LightGray) },
                visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color(0xFF00BFA5),
                    cursorColor = Color(0xFF00BFA5)
                ),
                singleLine = true
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }

    // Composable terpisah untuk dropdown desa
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun DesaDropdownField(
        selectedText: String,
        onDesaSelected: (String) -> Unit
    ) {
        val desaOptions = listOf("Desa Sukamandi", "Desa Sagalaherang Kaler", "Desa Sagalaherang Kidul")
        var expanded by remember { mutableStateOf(false) }

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            Text("Pilih Desa :", style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.SemiBold)
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded }
            ) {
                TextField(
                    value = selectedText,
                    onValueChange = {},
                    readOnly = true,
                    placeholder = { Text("Nama Desa", color = Color.LightGray) },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth(),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        focusedIndicatorColor = Color(0xFF00BFA5)
                    )
                )
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    desaOptions.forEach { option ->
                        DropdownMenuItem(
                            text = { Text(option) },
                            onClick = {
                                onDesaSelected(option)
                                expanded = false
                            }
                        )
                    }
                }
            }
        }
    }