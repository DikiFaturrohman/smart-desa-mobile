package com.example.smart_desa.presentation.ui.screen.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.smart_desa.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onTimeout: () -> Unit) {
    // Efek ini akan berjalan sekali saat composable pertama kali ditampilkan
    LaunchedEffect(true) {
        delay(2000L) // Tahan selama 2 detik
        onTimeout()  // Panggil aksi setelah timeout
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFF00BFA5), Color(0xFF00897B))
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.lambang_kabupaten_subang), // Panggil logo asli
            contentDescription = "Logo",
            modifier = Modifier
                .size(160.dp) // Ukuran total lingkaran putih
                .clip(CircleShape) // Buat bentuknya menjadi lingkaran
                .background(Color.White) // Beri latar belakang putih
                .padding(30.dp) // Beri padding di dalam lingkaran agar logo tidak menempel
        )
    }
}