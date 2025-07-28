package com.example.smart_desa.presentation.ui.screen.layanan

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smart_desa.presentation.ui.screen.home.HomeHeader

// Data class untuk merepresentasikan setiap item layanan
data class Layanan(
    val judul: String,
    val deskripsi: String
)

/**
 * Menampilkan daftar layanan administrasi yang tersedia di desa.
 */
@Composable
fun LayananScreen() {
    // Daftar layanan yang akan ditampilkan
    val daftarLayanan = listOf(
        Layanan("Surat Keterangan Kematian", "Surat resmi sebagai bukti awal kematian, syarat penting untuk mengurus Akta Kematian dan administrasi lainnya."),
        Layanan("Surat Keterangan Usaha", "Surat dari lurah/desa yang menerangkan keberadaan suatu usaha untuk keperluan administrasi."),
        Layanan("Surat Keterangan Beda Nama", "Surat resmi yang menyatakan beberapa nama berbeda pada dokumen merujuk orang sama."),
        Layanan("Surat Keterangan Tidak Mampu", "Surat resmi sebagai bukti awal status ekonomi, syarat penting untuk mengajukan bantuan atau beasiswa."),
        Layanan("Surat Keterangan Penghasilan", "Surat resmi yang merincikan penghasilan seseorang, biasanya untuk keperluan pinjaman atau administrasi."),
        Layanan("Surat Keterangan Status Pernikahan", "Surat resmi sebagai bukti status perkawinan seseorang, diperlukan untuk berbagai urusan administrasi."),
        Layanan("Surat Keterangan Riwayat Tanah", "Dokumen yang menjelaskan sejarah kepemilikan sebidang tanah untuk menghindari sengketa."),
        Layanan("Surat Keterangan Kelahiran", "Surat resmi dari desa sebagai dasar untuk pembuatan Akta Kelahiran di dinas terkait."),
        Layanan("Surat Keterangan Ahli Waris", "Dokumen resmi untuk menyatakan siapa saja yang berhak menjadi ahli waris dari seseorang yang telah meninggal."),
        Layanan("Surat Keterangan Lain/Sapu Jagat", "Surat keterangan untuk berbagai keperluan lain yang tidak tercantum secara spesifik dalam daftar.")

    )

    // Box sebagai container utama dengan latar belakang hijau
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF00BFA5)) // Warna hijau toska sesuai header
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Menggunakan kembali HomeHeader dari HomeScreen untuk konsistensi
            Spacer(modifier = Modifier.height(16.dp))
            Box(modifier = Modifier.padding(horizontal = 16.dp)){
                HomeHeader()
            }
            Spacer(modifier = Modifier.height(24.dp))

            // Card utama sebagai container untuk daftar layanan
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
            ) {
                // Kolom untuk menampung judul dan daftar
                Column(
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {
                    Spacer(modifier = Modifier.height(24.dp))
                    // Judul seksi "Layanan Administrasi"
                    Text(
                        text = "Layanan Administrasi",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    // LazyColumn untuk menampilkan daftar layanan agar efisien
                    LazyColumn(
                        contentPadding = PaddingValues(bottom = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(daftarLayanan) { layanan ->
                            LayananItemCard(layanan = layanan)
                        }
                    }
                }
            }
        }
    }
}

/**
 * Composable untuk menampilkan setiap item layanan dalam bentuk Card.
 * @param layanan Objek data layanan yang akan ditampilkan.
 */
@Composable
fun LayananItemCard(layanan: Layanan) {
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                // Aksi ketika item di-klik, misalnya menampilkan Toast
                Toast
                    .makeText(context, "Membuka ${layanan.judul}", Toast.LENGTH_SHORT)
                    .show()
                // TODO: Tambahkan navigasi ke halaman detail layanan
            },
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, Color.LightGray),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Judul layanan
            Text(
                text = layanan.judul,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(4.dp))
            // Deskripsi layanan
            Text(
                text = layanan.deskripsi,
                fontSize = 14.sp,
                color = Color.Gray
            )
        }
    }
}

