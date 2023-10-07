package com.example.mvp_mvvm

class LoginContract {

    interface View {
        fun setSuccess()
        fun setError(error : String)
        fun showProgress()
        fun hideProgress()

    }

    interface Presenter {
        fun onAttach(view: View)
        fun onLogin(login: String, password: String)
        fun onCredentialChanges()

    }


}