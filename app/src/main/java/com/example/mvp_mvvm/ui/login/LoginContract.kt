package com.example.mvp_mvvm.ui.login

import androidx.annotation.MainThread

class LoginContract {

    interface View {
        /** указываем состояния которые можно отображать на View */
        @MainThread
        fun setSuccess()

        @MainThread
        fun setError(error: String)

        @MainThread
        fun showProgress()

        @MainThread
        fun hideProgress()
    }

    interface Presenter {
        /** указываем операции которые делаются в Presenter на экране  */
        @MainThread
        fun onAttach(view: View)

        @MainThread
        fun onLogin(login: String, password: String)

        @MainThread
        fun onCredentialsChanged()
    }

}