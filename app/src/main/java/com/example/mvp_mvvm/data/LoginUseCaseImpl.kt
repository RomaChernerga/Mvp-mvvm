package com.example.mvp_mvvm.data

import android.os.Handler
import com.example.mvp_mvvm.domain.LoginApi
import com.example.mvp_mvvm.domain.LoginUseCase

class LoginUseCaseImpl(
    private val uiHandler:Handler,
    private val api: LoginApi
) : LoginUseCase {

    override fun login(login: String, password: String, callback: (Boolean) -> Unit) {
        Thread {
            val result = api.login(login, password)
            uiHandler.post {
                callback(result)
            }
        }.start()
    }
}