package com.example.smart_desa.presentation.ui.screen.galeri

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.smart_desa.R

data class FotoItem(val id: Int, val imageRes: Int)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FotoScreen(onBackPress: () -> Unit) {
    val daftarFoto = listOf(
        FotoItem(1, R.drawable.berita_subang),
        FotoItem(2, R.drawable.infografis),
        FotoItem(3, R.drawable.infograf),
        FotoItem(4, R.drawable.info),
        FotoItem(5, R.drawable.berita_subang),
        FotoItem(6, R.drawable.infografis)
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Galeri Foto") },
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
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(4.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(daftarFoto) { foto ->
                Image(
                    painter = painterResource(id = foto.imageRes),
                    contentDescription = "Foto ${foto.id}",
                    modifier = Modifier
                        .aspectRatio(1f),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}