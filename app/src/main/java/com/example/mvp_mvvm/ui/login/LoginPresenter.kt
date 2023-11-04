package com.example.mvp_mvvm.ui.login

import com.example.mvp_mvvm.domain.LoginUsecase

class LoginPresenter(
    private val loginUseCase: LoginUsecase
) : LoginContract.Presenter {

    private var view: LoginContract.View? = null
    private var onSuccess = false


    override fun onAttach(view: LoginContract.View) {
        this.view = view
        if (onSuccess) {
            view.setSuccess()
        }
    }

    override fun onLogin(login: String, password: String) {
        view?.showProgress()
        loginUseCase.login(login, password) { result ->
            view?.hideProgress()
            if (result) {
                view?.setSuccess()
                onSuccess = true
            } else {
                view?.setError("InvalidPassword")
                onSuccess = false
            }
        }

    }

    private fun checkUsers(login: String, password: String): Boolean {
        return login == password
    }

    override fun onCredentialChanges() {
        //todo
    }
}