package com.example.mvp_mvvm.ui.login

import androidx.annotation.MainThread
import com.example.mvp_mvvm.utils.Publisher

interface LoginContract {

    /** т.к. Presenter ничего не должен знать о View, мы удаляем его реализации отсюда */
//    interface View {
//        /** указываем состояния которые можно отображать на View */
//        @MainThread
//        fun setSuccess()
//
//        @MainThread
//        fun setError(error: String)
//
//        @MainThread
//        fun showProgress()
//
//        @MainThread
//        fun hideProgress()
//    }


    /**
     * class Activity {
     *      fun onCreate {
     *          viewModel.shouldShowProgress.subscribe {
     *              if (it) {
     *                  dialog.show()
     *              } else {
     *                  dialog.dismiss()}
     *          }
     *      }
     *  }
     * */
    interface ViewModel {
        val shouldShowProgress : Publisher<Boolean>
        val isSuccess: Publisher<Boolean>
        val errorText: Publisher<String?>

        @MainThread
        fun onLogin(login: String, password: String)

        @MainThread
        fun onCredentialsChanged()
    }

}