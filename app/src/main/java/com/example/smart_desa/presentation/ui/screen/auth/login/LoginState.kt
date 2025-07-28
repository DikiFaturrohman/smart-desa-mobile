package com.example.smart_desa.presentation.ui.screen.auth.login

// Merepresentasikan state UI untuk layar Login.
data class LoginState(
    val nik: String="",
    val password: String="",
    val isLoading: Boolean = false
)