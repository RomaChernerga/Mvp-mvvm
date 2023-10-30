package com.example.mvp_mvvm.ui.login

import com.example.mvp_mvvm.domain.LoginUsecase
import com.example.mvp_mvvm.utils.Publisher

class LoginViewModel(private val loginUsecase: LoginUsecase) : LoginContract.ViewModel {

    override val shouldShowProgress: Publisher<Boolean> = Publisher()
    override val isSuccess: Publisher<Boolean> = Publisher()
    override val errorText: Publisher<String?> = Publisher(isSingle = true)


    override fun onLogin(login: String, password: String) {
        shouldShowProgress.post(true)

        loginUsecase.login(login, password) { result ->

            shouldShowProgress.post(false)
            if (result) {
                isSuccess.post(true)
                errorText.post("")
            } else {
                errorText.post("Invalid password")
                isSuccess.post(false)
            }

        }
    }

    override fun onCredentialsChanged() {
        // todo
    }
}