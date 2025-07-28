// In file: presentation/ui/screen/profile/EditProfileScreen.kt
package com.example.smart_desa.presentation.ui.screen.profile

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smart_desa.R

/**
 * @param onBackPress Callback untuk kembali ke halaman sebelumnya.
 * @param onSave Callback yang dipanggil saat tombol "Simpan" ditekan.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileScreen(
    onBackPress: () -> Unit,
    onSave: () -> Unit
) {
    // State untuk menampung nilai input field. Diisi dengan data awal.
    var namaLengkap by remember { mutableStateOf("John Doe") }
    var email by remember { mutableStateOf("john@example.com") }
    var nomorTelepon by remember { mutableStateOf("08123456789") }
    val nik = "3213010120010001" // NIK tidak bisa diubah

    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Edit Profil", color = Color.White) },
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
                    containerColor = Color(0xFF00BFA5)
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // Foto Profil
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "Foto Profil",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
                    .background(Color.LightGray)
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Field NIK
            EditProfileInputField(
                label = "NIK",
                value = nik,
                onValueChange = { /* Tidak melakukan apa-apa */ },
                enabled = false
            )

            // Field Nama Lengkap
            EditProfileInputField(
                label = "Nama Lengkap",
                value = namaLengkap,
                onValueChange = { namaLengkap = it }
            )

            // Field Email
            EditProfileInputField(
                label = "Email",
                value = email,
                onValueChange = { email = it },
                keyboardType = KeyboardType.Email
            )

            // Field Nomor Telepon
            EditProfileInputField(
                label = "Nomor Telepon",
                value = nomorTelepon,
                onValueChange = { nomorTelepon = it },
                keyboardType = KeyboardType.Phone
            )

            Spacer(modifier = Modifier.weight(1f))

            // Tombol Simpan
            Button(
                onClick = {
                    Toast.makeText(context, "Profil berhasil diperbarui", Toast.LENGTH_SHORT).show()
                    onSave()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00BFA5))
            ) {
                Text(text = "Simpan Perubahan", fontSize = 16.sp)
            }
        }
    }
}

/**
 * Composable reusable untuk input field di halaman Edit Profil.
 */
@Composable
private fun EditProfileInputField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    keyboardType: KeyboardType = KeyboardType.Text,
    enabled: Boolean = true
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
            color = Color.Gray
        )
        TextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                focusedIndicatorColor = Color(0xFF00BFA5),
                cursorColor = Color(0xFF00BFA5),
                disabledTextColor = Color.Black
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
}