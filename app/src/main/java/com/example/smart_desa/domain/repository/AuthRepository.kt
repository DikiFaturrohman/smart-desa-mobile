package com.example.smart_desa.domain.repository

interface AuthRepository {
    suspend fun login(nik: String, password: String): Boolean
}