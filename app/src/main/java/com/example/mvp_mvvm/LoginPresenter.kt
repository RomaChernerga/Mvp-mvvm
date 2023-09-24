package com.example.mvp_mvvm

import android.os.Handler
import android.os.Looper
import java.lang.Thread.sleep


class LoginPresenter : LoginContract.Presenter {
    private var view: LoginContract.View? = null
    private val uiHandler = Handler(Looper.getMainLooper())

    override fun onAttach(view: LoginContract.View) {
        this.view = view
    }

    override fun onLogin(login: String, password: String) {
        view?.showProgress()
        Thread {
            sleep(3_000)
            uiHandler.post{
                view?.hideProgress()
                if (checkCredentials(login, password)) {
                    view?.setSuccess()
                } else {
                    view?.setError("Invalid password")
                }
            }

        }.start()
    }

    private fun checkCredentials(login: String, password: String): Boolean {
        return login == password
    }

    override fun onCredentialsChanged() {
        // todo
    }
}