package com.example.smart_desa.presentation.ui.screen.auth.register

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


@Composable
fun RegisterScreen(navController: NavController) {
    var nik by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var namaLengkap by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Register Akun", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        // Sesuai dokumen F-3, bisa ditambahkan field lainnya
        OutlinedTextField(value = nik, onValueChange = { nik = it }, label = { Text("NIK") })
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(value = password, onValueChange = { password = it }, label = { Text("Password") })
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(value = namaLengkap, onValueChange = { namaLengkap = it }, label = { Text("Nama Lengkap") })
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                // Simulasi registrasi berhasil
                Toast.makeText(context, "Registrasi Berhasil, Silakan Login", Toast.LENGTH_SHORT).show()
                navController.popBackStack() // Kembali ke layar login
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Daftar")
        }
    }
}