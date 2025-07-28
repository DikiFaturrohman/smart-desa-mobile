package com.example.smart_desa.presentation.ui.screen.pengajuan

// Enum untuk status progres, agar lebih aman dari kesalahan pengetikan
enum class StatusProgres {
    SELESAI,
    MENUNGGU
}

// Data untuk setiap item dalam daftar pengajuan
data class PengajuanItem(
    val id: Int,
    val jenisSurat: String,
    val tanggal: String
)

// Data untuk setiap langkah di halaman detail progres
data class ProgressStep(
    val deskripsi: String,
    val tanggal: String = "",
    val status: StatusProgres
)