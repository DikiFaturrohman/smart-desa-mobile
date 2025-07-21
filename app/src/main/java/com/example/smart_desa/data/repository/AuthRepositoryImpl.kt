package com.example.smart_desa.data.repository

import com.example.smart_desa.domain.repository.AuthRepository
import kotlinx.coroutines.delay

class AuthRepositoryImpl : AuthRepository {
    override suspend fun login(nik: String, password: String): Boolean {
        // Simulasi proses network/database check
        delay(1000L)
        return nik == "12345" && password == "password"
    }
}