package com.example.smart_desa.presentation.ui.screen.dokumen

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.AddPhotoAlternate
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

/**
 * @param onBackPress Callback untuk kembali ke halaman sebelumnya.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UnggahDokumenScreen(
    onBackPress: () -> Unit
) {
    val context = LocalContext.current

    // State untuk menyimpan URI dari gambar yang dipilih
    var ktpUri by remember { mutableStateOf<Uri?>(null) }
    var kkUri by remember { mutableStateOf<Uri?>(null) }

    // Launcher untuk KTP
    val ktpPhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) { uri ->
        if (uri != null) {
            ktpUri = uri
        }
    }

    // Launcher untuk KK
    val kkPhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) { uri ->
        if (uri != null) {
            kkUri = uri
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Unggah Dokumen") },
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
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Komponen untuk unggah KTP
            DocumentUploader(
                title = "Foto KTP",
                imageUri = ktpUri,
                onSelectClick = {
                    ktpPhotoPickerLauncher.launch(
                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                    )
                }
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Komponen untuk unggah KK
            DocumentUploader(
                title = "Foto Kartu Keluarga (KK)",
                imageUri = kkUri,
                onSelectClick = {
                    kkPhotoPickerLauncher.launch(
                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                    )
                }
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Tombol Simpan
            Button(
                onClick = {
                    if (ktpUri != null && kkUri != null) {
                        Toast.makeText(context, "Dokumen sedang diunggah...", Toast.LENGTH_SHORT).show()
                        // TODO: Tambahkan logika untuk mengirim file ke server/API
                    } else {
                        Toast.makeText(context, "Harap lengkapi semua dokumen", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00BFA5)),
                contentPadding = PaddingValues(vertical = 14.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(text = "Simpan Dokumen", fontSize = 16.sp)
            }
        }
    }
}

/**
 * @param title Judul area unggah (e.g., "Foto KTP").
 * @param imageUri URI dari gambar yang dipilih untuk pratinjau.
 * @param onSelectClick Aksi yang dijalankan saat tombol pilih file diklik.
 */
@Composable
fun DocumentUploader(
    title: String,
    imageUri: Uri?,
    onSelectClick: () -> Unit
) {
    Column(horizontalAlignment = Alignment.Start, modifier = Modifier.fillMaxWidth()) {
        Text(text = title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
        Spacer(modifier = Modifier.height(8.dp))
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clickable(onClick = onSelectClick),
            shape = RoundedCornerShape(12.dp),
            border = BorderStroke(1.dp, Color.LightGray)
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                if (imageUri != null) {
                    // Tampilkan gambar pratinjau jika sudah dipilih
                    AsyncImage(
                        model = imageUri,
                        contentDescription = "Pratinjau $title",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    // Tampilkan placeholder jika belum ada gambar
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.AddPhotoAlternate,
                            contentDescription = "Ikon unggah",
                            modifier = Modifier.size(48.dp),
                            tint = Color.Gray
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = "Pilih file...", color = Color.Gray)
                    }
                }
            }
        }
    }
}