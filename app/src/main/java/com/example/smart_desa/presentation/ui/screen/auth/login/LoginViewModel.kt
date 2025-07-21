package com.example.smart_desa.presentation.ui.screen.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smart_desa.data.repository.AuthRepositoryImpl // Ganti dengan DI di proyek nyata
import com.example.smart_desa.domain.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel     (
    private val repository: AuthRepository = AuthRepositoryImpl()
) : ViewModel() {
    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    fun onNikChange(nik: String) {
        _state.update { it.copy(nik = nik) }
    }

    fun onPasswordChange(password: String) {
        _state.update { it.copy(password = password) }
    }

    fun login(onLoginSuccess:() -> Unit, onLoginFailed: () -> Unit) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            val isSuccess = repository.login(
                nik = state.value.nik,
                password = state.value.password
            )

            if (isSuccess){
                onLoginSuccess()
            } else {
                onLoginFailed()
            }
            _state.update { it.copy(isLoading = false) }
        }
    }
}