package com.example.mvp_mvvm.data

import com.example.mvp_mvvm.domain.LoginApi

class WebLoginApiImpl : LoginApi {

    fun salute() {

    }

    override fun login(login: String, password: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun register(login: String, password: String, email: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun logout(): Boolean {
        TODO("Not yet implemented")
    }

    override fun forgotPassword(login: String): Boolean {
        TODO("Not yet implemented")
    }
}