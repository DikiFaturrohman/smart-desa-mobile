package com.example.smart_desa.presentation.ui.screen.profile

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smart_desa.R // Pastikan Anda memiliki gambar di res/drawable
import com.example.smart_desa.presentation.ui.screen.home.HomeHeader

/** Model data untuk informasi pengguna. */
data class UserData(
    val nik: String,
    val namaLengkap: String,
    val email: String,
    val nomorTelepon: String,
    val desa: String,
    val photo: Int // Resource ID drawable
)

/**
 * Menampilkan detail profil pengguna dan opsi untuk logout atau mengedit profil.
 * @param onLogout Aksi untuk keluar dari akun.
 * @param onNavigateToEditProfile Aksi untuk pindah ke layar edit profil.
 */
@Composable
fun ProfileScreen(
    onLogout: () -> Unit,
    onNavigateToEditProfile: () -> Unit
) {
    // Data pengguna dummy untuk tampilan.
    val user = UserData(
        nik = "3213010120010001",
        namaLengkap = "John Doe",
        email = "john@example.com",
        nomorTelepon = "08123456789",
        desa = "Desa Sagalaherang",
        photo = R.drawable.ic_launcher_foreground
    )
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF00BFA5))
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Spacer(modifier = Modifier.height(16.dp))
            Box(modifier = Modifier.padding(horizontal = 16.dp)) {
                HomeHeader()
            }
            Spacer(modifier = Modifier.height(24.dp))

            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp)
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Profil",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(24.dp))

                    // Foto Profil
                    Image(
                        painter = painterResource(id = user.photo),
                        contentDescription = "Foto Profil",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(120.dp)
                            .clip(CircleShape)
                    )
                    Spacer(modifier = Modifier.height(24.dp))

                    // Detail Informasi Pengguna
                    ProfileInfoItem(label = "NIK :", value = user.nik)
                    ProfileInfoItem(label = "Nama Lengkap :", value = user.namaLengkap)
                    ProfileInfoItem(label = "Email :", value = user.email)
                    ProfileInfoItem(label = "Nomor Telepon :", value = user.nomorTelepon)
                    ProfileInfoItem(label = "Desa", value = user.desa)

                    Spacer(modifier = Modifier.weight(1f))

                    // Tombol Edit Profil
                    Button(
                        onClick = {
                            onNavigateToEditProfile()
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00BFA5)),
                        contentPadding = PaddingValues(vertical = 14.dp)
                    ) {
                        Text(text = "Edit Profil", fontSize = 16.sp)
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Tombol Logout
                    Button(
                        onClick = onLogout, // Menggunakan callback onLogout
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF44336)), // Warna merah
                        contentPadding = PaddingValues(vertical = 14.dp)
                    ) {
                        Text(text = "Logout", fontSize = 16.sp)
                    }
                }
            }
        }
    }
}

/**
 * Composable reusable untuk menampilkan satu baris informasi profil.
 * @param label Teks label (e.g., "NIK :").
 * @param value Teks nilai (e.g., "32130...").
 */
@Composable
fun ProfileInfoItem(label: String, value: String) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = label,
            fontSize = 14.sp,
            color = Color.Gray
        )
        Text(
            text = value,
            fontSize = 16.sp,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        Divider(color = Color.LightGray, thickness = 1.dp)
        Spacer(modifier = Modifier.height(16.dp))
    }
}