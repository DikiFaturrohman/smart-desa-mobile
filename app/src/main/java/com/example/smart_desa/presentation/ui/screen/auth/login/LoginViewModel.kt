package com.example.smart_desa.presentation.ui.screen.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smart_desa.data.repository.AuthRepositoryImpl // Ganti dengan DI di proyek nyata
import com.example.smart_desa.domain.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * ViewModel untuk mengelola state dan business logic dari login screen
 * Menggunakan StateFlow untuk reactive state management
 *
 * @param repository Repository interface untuk operasi autentikasi
 */
class LoginViewModel(
    private val repository: AuthRepository = AuthRepositoryImpl() // Dependency injection manual
) : ViewModel() {

    // === STATE MANAGEMENT ===
    // Private MutableStateFlow untuk internal state management
    private val _state = MutableStateFlow(LoginState())
    // Public StateFlow yang dapat diobserve oleh UI layer
    val state = _state.asStateFlow()

    /**
     * Fungsi untuk mengupdate nilai NIK dalam state
     * Menggunakan update function untuk memastikan immutability
     *
     * @param nik String nilai NIK yang baru diinput pengguna
     */
    fun onNikChange(nik: String) {
        _state.update { it.copy(nik = nik) }
    }

    /**
     * Fungsi untuk mengupdate nilai password dalam state
     * Menggunakan update function untuk memastikan immutability
     *
     * @param password String nilai password yang baru diinput pengguna
     */
    fun onPasswordChange(password: String) {
        _state.update { it.copy(password = password) }
    }

    /**
     * Fungsi utama untuk melakukan proses login
     * Menjalankan autentikasi secara asynchronous menggunakan coroutines
     *
     * @param onLoginSuccess Callback yang dipanggil jika login berhasil
     * @param onLoginFailed Callback yang dipanggil jika login gagal
     */
    fun login(onLoginSuccess:() -> Unit, onLoginFailed: () -> Unit) {
        // Menjalankan dalam viewModelScope untuk lifecycle-aware coroutines
        viewModelScope.launch {
            // Set loading state menjadi true sebelum memulai proses
            _state.update { it.copy(isLoading = true) }

            // Memanggil repository untuk melakukan autentikasi
            val isSuccess = repository.login(
                nik = state.value.nik, // Mengambil NIK dari current state
                password = state.value.password // Mengambil password dari current state
            )

            // Menjalankan callback berdasarkan hasil autentikasi
            if (isSuccess){
                onLoginSuccess() // Panggil callback sukses
            } else {
                onLoginFailed() // Panggil callback gagal
            }

            // Set loading state menjadi false setelah proses selesai
            _state.update { it.copy(isLoading = false) }
        }
    }
}