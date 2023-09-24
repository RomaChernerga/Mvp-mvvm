package com.example.mvp_mvvm

import android.os.Handler

class LoginContract {

    interface View {
        /** указываем состояния которые можно отображать на View */
        fun setSuccess()
        fun setError(error: String)
        fun showProgress()
        fun hideProgress()
    }

    interface Presenter {
        /** указываем операции которые делаются в Presenter на экране  */
        fun onAttach(view: View)
        fun onLogin(login: String, password: String)
        fun onCredentialsChanged()
    }

}