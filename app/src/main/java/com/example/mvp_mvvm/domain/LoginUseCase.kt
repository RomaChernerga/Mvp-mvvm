package com.example.mvp_mvvm.domain

import androidx.annotation.MainThread

/** ИНТЕРРАПТОР  */
interface LoginUseCase {
    fun login(
        login: String,
        password: String,
        @MainThread callback: (Boolean) -> Unit)
}