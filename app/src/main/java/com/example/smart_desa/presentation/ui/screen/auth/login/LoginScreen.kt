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

    val primaryColor = Color(0xFF00BFA5)


    // Container
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Header
            // Box Header
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f) // 1/3 Screen
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(primaryColor, Color(0xFF00897B))
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {
                // Logo
                Image(
                    painter = painterResource(id = R.drawable.lambang_kabupaten_subang),
                    contentDescription = "Logo Desa",
                    modifier = Modifier
                        .size(120.dp)
                        .shadow(8.dp, CircleShape)
                        .clip(CircleShape)
                        .background(Color.White)
                        .padding(12.dp),
                    contentScale = ContentScale.Fit
                )
            }

            // Form
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(2f) // 2/3 Screen
                    .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(horizontal = 24.dp, vertical = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Selamat Datang,",
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Daftar sekarang untuk mendapatkan akses penuh layanan kami.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(24.dp))

                // Input Field
                Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start) {

                    Text("NIK :", style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.SemiBold)
                    // TextField
                    TextField(
                        value = state.nik, // Value dari state ViewModel
                        onValueChange = loginViewModel::onNikChange, // Callback ke ViewModel
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = { Text("NIK", color = Color.LightGray) },
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            focusedIndicatorColor = primaryColor,
                            cursorColor = primaryColor
                        ),
                        singleLine = true,
                        enabled = !state.isLoading
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))

                // Input Field Password
                var passwordVisible by remember { mutableStateOf(false) }

                Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start) {

                    Text("Password :", style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.SemiBold)
                    // TextField
                    TextField(
                        value = state.password, // Value dari state ViewModel
                        onValueChange = loginViewModel::onPasswordChange, // Callback ke ViewModel
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = { Text("Password", color = Color.LightGray) },
                        // Visual Transform
                        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            focusedIndicatorColor = primaryColor,
                            cursorColor = primaryColor
                        ),
                        singleLine = true,
                        enabled = !state.isLoading,

                        trailingIcon = {
                            val image = if (passwordVisible)
                                Icons.Filled.VisibilityOff
                            else Icons.Filled.Visibility

                            val description = if (passwordVisible) "Sembunyikan password" else "Tampilkan password"

                            // IconButton State Password
                            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                Icon(imageVector = image, contentDescription = description)
                            }
                        }
                    )
                }
                // Forgot Password
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
                    TextButton(onClick = { onNavigateToForgotPassword() }) {
                        Text("Lupa Kata Sandi?", color = Color.Gray)
                    }
                }
                Spacer(modifier = Modifier.height(16.dp)) // Spacer 16dp

                // Login Button
                Button(
                    onClick = {
                        // Memanggil fungsi login dari ViewModel dengan callback
                        loginViewModel.login(
                            onLoginSuccess = onLoginSuccess,
                            onLoginFailed = {

                                Toast.makeText(context, "NIK atau Password Salah", Toast.LENGTH_SHORT).show()
                            }
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = primaryColor),
                    enabled = !state.isLoading
                ) {
                    Text("Masuk", fontSize = 16.sp)
                }

                Spacer(modifier = Modifier.weight(1f))

                // Register
                ClickableText(
                    text = buildAnnotatedString {

                        withStyle(style = SpanStyle(color = Color.Gray)) {
                            append("Belum punya akun? ")
                        }

                        withStyle(style = SpanStyle(color = primaryColor, fontWeight = FontWeight.Bold)) {
                            append("Daftar")
                        }
                    },
                    onClick = { onNavigateToRegister() }
                )
            }
        }

        // Loading Indicator
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}