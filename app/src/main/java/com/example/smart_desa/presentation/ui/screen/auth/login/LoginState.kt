package com.example.smart_desa.presentation.ui.screen.auth.login

/**
 * Data class yang merepresentasikan state dari login screen
 * Menggunakan immutable data class untuk state management yang aman
 */

data class LoginState(
    val nik: String="",
    val password: String="",
    val isLoading: Boolean = false
)