package com.example.mvp_mvvm

import android.os.Handler
import android.os.Looper
import java.lang.Thread.sleep

class LoginPresenter : LoginContract.Presenter {

    private val uiHandler = Handler(Looper.getMainLooper())
    private var view: LoginContract.View? = null
    private var onSuccess = false



    override fun onAttach(view: LoginContract.View) {
        this.view = view
        if (onSuccess) {
            view.setSuccess()
        } else {
            view.setError("Invalid Login")
        }
    }

    override fun onLogin(login: String, password: String) {
        view?.showProgress()
        Thread {
            sleep(3_000)
            uiHandler.post {
                view?.hideProgress()
                if (checkUsers(login, password)) {
                    view?.setSuccess()
                    onSuccess = true
                } else {
                    view?.setError("InvalidPassword")
                    onSuccess = false
                }
            }
        }.start()

    }

    private fun checkUsers(login: String, password: String): Boolean {
        return login == password
    }

    override fun onCredentialChanges() {
        //todo
    }
}