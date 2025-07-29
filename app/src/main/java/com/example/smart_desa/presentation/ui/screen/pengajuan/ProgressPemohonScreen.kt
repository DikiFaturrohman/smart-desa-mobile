package com.example.smart_desa.presentation.ui.screen.pengajuan

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.HourglassEmpty
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smart_desa.presentation.ui.screen.home.HomeHeader

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProgressPemohonScreen(
    pengajuanId: Int,
    onBackPress: () -> Unit
) {
    // Data progres
    val progressSteps = listOf(
        ProgressStep("Pengajuan Surat Keterangan Kematian", "01 Januari 2025", StatusProgres.SELESAI),
        ProgressStep("Dalam Proses Verifikasi Operator Desa", status = StatusProgres.MENUNGGU),
        ProgressStep("Kasi Desa Menunggu Data", status = StatusProgres.MENUNGGU),
        ProgressStep("Sekretaris Menunggu Data", status = StatusProgres.MENUNGGU),
        ProgressStep("Kepala Desa Menunggu Data", status = StatusProgres.MENUNGGU)
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Progres Pemohon") },
                navigationIcon = {
                    IconButton(onClick = onBackPress) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Kembali")
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
        ) {
            Text(
                text = "Surat Keterangan Kematian",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(24.dp))
            LazyColumn {
                itemsIndexed(progressSteps) { index, step ->
                    ProgressStepItem(
                        step = step,
                        isLastItem = index == progressSteps.lastIndex
                    )
                }
            }
        }
    }
}

@Composable
fun ProgressStepItem(step: ProgressStep, isLastItem: Boolean) {
    val icon = if (step.status == StatusProgres.SELESAI) Icons.Default.CheckCircle else Icons.Default.HourglassEmpty
    val color = if (step.status == StatusProgres.SELESAI) Color(0xFF00BFA5) else Color.Gray

    Row(modifier = Modifier.fillMaxWidth()) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                imageVector = icon,
                contentDescription = step.status.name,
                tint = color,
                modifier = Modifier.size(32.dp)
            )
            if (!isLastItem) {
                Divider(
                    modifier = Modifier
                        .height(60.dp)
                        .width(2.dp),
                    color = Color.LightGray
                )
            }
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column(
            modifier = Modifier.padding(top = 4.dp)
        ) {
            Text(
                text = step.deskripsi,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                color = if (step.status == StatusProgres.SELESAI) Color.Black else Color.Gray
            )
            if (step.tanggal.isNotBlank()) {
                Text(
                    text = step.tanggal,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
        }
    }
}