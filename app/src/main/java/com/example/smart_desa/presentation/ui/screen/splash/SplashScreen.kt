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

/**
 * Layar pembuka (splash screen) yang tampil selama 2 detik.
 * @param onTimeout Aksi yang dijalankan setelah durasi selesai.
 */
@Composable
fun SplashScreen(onTimeout: () -> Unit) {
    // Jalankan aksi setelah 2 detik
    LaunchedEffect(true) {
        delay(2000L)
        onTimeout()
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
                .size(160.dp)
                .clip(CircleShape)
                .background(Color.White)
                .padding(30.dp)
        )
    }
}