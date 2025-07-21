package com.example.smart_desa.presentation.ui.screen.auth.login

data class LoginState(
    val nik: String="",
    val password: String="",
    val isLoading: Boolean = false
)