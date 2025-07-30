package com.example.smart_desa.presentation.ui.screen.layanan

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailLayananScreen(
    layananTitle: String,
    onBackPress: () -> Unit,
    onAjukan: () -> Unit
) {
    var keperluan by remember { mutableStateOf("") }
    var catatan by remember { mutableStateOf("") }
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(layananTitle) },
                navigationIcon = {
                    IconButton(onClick = onBackPress) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Kembali")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF00BFA5),
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = "Form Pengajuan",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Silakan lengkapi data di bawah ini untuk mengajukan permohonan $layananTitle.",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(24.dp))

            // Input field untuk Keperluan
            OutlinedTextField(
                value = keperluan,
                onValueChange = { keperluan = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Keperluan") },
                placeholder = { Text("Contoh: Untuk melamar pekerjaan") },
                singleLine = true
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Input field untuk Catatan
            OutlinedTextField(
                value = catatan,
                onValueChange = { catatan = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                label = { Text("Catatan Tambahan (Opsional)") },
                placeholder = { Text("Tuliskan catatan jika ada...") }
            )
            Spacer(modifier = Modifier.height(32.dp))

            // Tombol Ajukan
            Button(
                onClick = {
                    if (keperluan.isNotBlank()) {
                        Toast.makeText(context, "Pengajuan berhasil dikirim!", Toast.LENGTH_SHORT).show()
                        onAjukan()
                    } else {
                        Toast.makeText(context, "Harap isi kolom Keperluan", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier.fillMaxWidth().height(50.dp)
            ) {
                Text("Ajukan Permohonan")
            }
        }
    }
}