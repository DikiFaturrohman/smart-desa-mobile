package com.example.smart_desa.presentation.ui.screen.auth.register

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smart_desa.R

/**
 * Halaman registrasi dengan berbagai input field dan validasi data.
 * @param onRegisterSuccess Aksi setelah registrasi berhasil.
 * @param onBackPress Aksi untuk kembali ke layar sebelumnya.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    onRegisterSuccess: () -> Unit,
    onBackPress: () -> Unit
) {
    // State untuk input form
    var nik by remember { mutableStateOf("") }
    var namaLengkap by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var konfirmasiPassword by remember { mutableStateOf("") }
    var nomorTelepon by remember { mutableStateOf("") }
    var alamatLengkap by remember { mutableStateOf("") }
    var tanggalLahir by remember { mutableStateOf("") }
    var selectedDesa by remember { mutableStateOf("") }

    // State untuk pesan error validasi
    var nikError by remember { mutableStateOf<String?>(null) }
    var namaLengkapError by remember { mutableStateOf<String?>(null) }
    var emailError by remember { mutableStateOf<String?>(null) }
    var passwordError by remember { mutableStateOf<String?>(null) }
    var konfirmasiPasswordError by remember { mutableStateOf<String?>(null) }
    var nomorTeleponError by remember { mutableStateOf<String?>(null) }
    var alamatLengkapError by remember { mutableStateOf<String?>(null) }
    var tanggalLahirError by remember { mutableStateOf<String?>(null) }
    var desaError by remember { mutableStateOf<String?>(null) }



    val context = LocalContext.current
    val primaryColor = Color(0xFF00BFA5)

    // Fungsi untuk memvalidasi semua input sebelum submit
    fun validate(): Boolean {
        // Reset semua error
        nikError = null
        namaLengkapError = null
        emailError = null
        passwordError = null
        konfirmasiPasswordError = null
        nomorTeleponError = null
        alamatLengkapError = null
        tanggalLahirError = null
        desaError = null

        var isValid = true

        if (nik.length != 16 || !nik.all { it.isDigit() }) {
            nikError = "NIK harus terdiri dari 16 digit angka."
            isValid = false
        }
        if (namaLengkap.isBlank()) {
            namaLengkapError = "Nama lengkap tidak boleh kosong."
            isValid = false
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailError = "Format email tidak valid."
            isValid = false
        }
        if (password.isBlank()) {
            passwordError = "Password tidak boleh kosong."
            isValid = false
        }
        if (password != konfirmasiPassword) {
            konfirmasiPasswordError = "Konfirmasi password tidak cocok."
            isValid = false
        }
        if (nomorTelepon.isBlank()) {
            nomorTeleponError = "Nomor telepon tidak boleh kosong."
            isValid = false
        }
        if (alamatLengkap.isBlank()) {
            alamatLengkapError = "Alamat lengkap tidak boleh kosong."
            isValid = false
        }
        if (tanggalLahir.isBlank()) {
            tanggalLahirError = "Tanggal lahir tidak boleh kosong."
            isValid = false
        }
        if (selectedDesa.isBlank()) {
            desaError = "Silakan pilih desa."
            isValid = false
        }

        return isValid
    }


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
        containerColor = primaryColor
    ) { paddingValues ->

        Column(
            modifier = Modifier.fillMaxSize()
        ) {
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

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(2.3f)
                    .background(MaterialTheme.colorScheme.surface),
                contentPadding = PaddingValues(
                    top = 24.dp,
                    bottom = 24.dp,
                    start = 24.dp,
                    end = 24.dp
                ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    FormInputField(
                        label = "NIK :",
                        value = nik,
                        placeholder = "16 Digit NIK",
                        onValueChange = { if (it.length <= 16 && it.all { char -> char.isDigit() }) nik = it },
                        error = nikError,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                }
                item {
                    FormInputField(
                        label = "Nama Lengkap :",
                        value = namaLengkap,
                        placeholder = "Nama Lengkap",
                        onValueChange = { namaLengkap = it },
                        error = namaLengkapError
                    )
                }
                item {
                    FormInputField(
                        label = "Email :",
                        value = email,
                        placeholder = "contoh@email.com",
                        onValueChange = { email = it },
                        error = emailError,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
                    )
                }
                item {
                    FormInputField(
                        label = "Password :",
                        value = password,
                        placeholder = "Password",
                        onValueChange = { password = it },
                        isPassword = true,
                        error = passwordError
                    )
                }
                item {
                    FormInputField(
                        label = "Konfirmasi Password :",
                        value = konfirmasiPassword,
                        placeholder = "Konfirmasi Password",
                        onValueChange = { konfirmasiPassword = it },
                        isPassword = true,
                        error = konfirmasiPasswordError
                    )
                }
                item {
                    FormInputField(
                        label = "Nomor Telepon :",
                        value = nomorTelepon,
                        placeholder = "08xxxxxxxxxx",
                        onValueChange = { nomorTelepon = it },
                        error = nomorTeleponError,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
                    )
                }
                item {
                    FormInputField(
                        label = "Alamat Lengkap :",
                        value = alamatLengkap,
                        placeholder = "Alamat Lengkap",
                        onValueChange = { alamatLengkap = it },
                        error = alamatLengkapError
                    )
                }
                item {
                    FormInputField(
                        label = "Tanggal Lahir :",
                        value = tanggalLahir,
                        placeholder = "DD/MM/YYYY",
                        onValueChange = { tanggalLahir = it },
                        error = tanggalLahirError
                    )
                }
                item {
                    DesaDropdownField(
                        selectedText = selectedDesa,
                        onDesaSelected = { selectedDesa = it },
                        error = desaError
                    )
                }

                item {
                    Spacer(modifier = Modifier.height(24.dp))

                    Button(
                        onClick = {
                            if (validate()) {
                                Toast.makeText(context, "Registrasi Berhasil, Silakan Login", Toast.LENGTH_SHORT).show()
                                onRegisterSuccess()
                            } else {
                                Toast.makeText(context, "Harap periksa kembali data Anda", Toast.LENGTH_SHORT).show()
                            }
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

// Komponen input teks yang dapat digunakan kembali untuk form.
@Composable
private fun FormInputField(
    label: String,
    value: String,
    placeholder: String,
    onValueChange: (String) -> Unit,
    isPassword: Boolean = false,
    error: String? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default
) {
    var passwordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            label,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.SemiBold
        )

        TextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(placeholder, color = Color.LightGray)
            },
            visualTransformation = if (isPassword && !passwordVisible) {
                PasswordVisualTransformation()
            } else {
                VisualTransformation.None
            },
            trailingIcon = {
                if (isPassword) {
                    val image = if (passwordVisible)
                        Icons.Filled.VisibilityOff
                    else
                        Icons.Filled.Visibility

                    val description = if (passwordVisible) "Sembunyikan password" else "Tampilkan password"

                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(imageVector = image, contentDescription = description)
                    }
                }
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = if (error != null) MaterialTheme.colorScheme.error else Color(0xFF00BFA5),
                unfocusedIndicatorColor = if (error != null) MaterialTheme.colorScheme.error else Color.LightGray,
                cursorColor = Color(0xFF00BFA5)
            ),
            singleLine = true,
            isError = error != null,
            keyboardOptions = keyboardOptions
        )

        if (error != null) {
            Text(
                text = error,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 4.dp, top = 4.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}


// Komponen dropdown yang dapat digunakan untuk form.
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DesaDropdownField(
    selectedText: String,
    onDesaSelected: (String) -> Unit,
    error: String? = null
) {
    val desaOptions = listOf(
        "Desa Sukamandi",
        "Desa Sagalaherang Kaler",
        "Desa Sagalaherang Kidul"
    )

    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            "Pilih Desa :",
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.SemiBold
        )

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            TextField(
                value = selectedText,
                onValueChange = {},
                readOnly = true,
                placeholder = {
                    Text("Nama Desa", color = Color.LightGray)
                },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                },
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = if (error != null) MaterialTheme.colorScheme.error else Color(0xFF00BFA5),
                    unfocusedIndicatorColor = if (error != null) MaterialTheme.colorScheme.error else Color.LightGray
                ),
                isError = error != null
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

        if (error != null) {
            Text(
                text = error,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 4.dp, top = 4.dp)
            )
        }
    }
}