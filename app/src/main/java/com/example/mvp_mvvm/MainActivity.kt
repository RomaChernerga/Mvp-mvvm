package com.example.mvp_mvvm

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.MainThread
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.mvp_mvvm.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), LoginContract.View {

    private lateinit var binding: ActivityMainBinding
    private var presenter: LoginContract.Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter = LoginPresenter()
        presenter?.onAttach(this)

        binding.buttonEnter.setOnClickListener {
            presenter?.onLogin(
                binding.loginEdText.text.toString(),
                binding.passwordEdText.text.toString()
            )
        }
    }
    @MainThread
    override fun setSuccess() {

        binding.buttonEnter.isVisible = false
        binding.loginEdText.isVisible = false
        binding.passwordEdText.isVisible = false
        binding.root.setBackgroundColor(Color.GREEN)

    }

    override fun setError(error: String) {
        Toast.makeText(this, "Error: $error", Toast.LENGTH_SHORT).show()
    }
    @MainThread
    override fun showProgress() {
        binding.buttonEnter.isEnabled = false
        hideKeyboard(this)
    }
    @MainThread
    override fun hideProgress() {

        binding.buttonEnter.isEnabled = true

    }



    private fun hideKeyboard(activity: Activity) {
        val imm = activity.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        //Find the currently focused view, so we can grab the correct window token from it.
        var view: View? = activity.currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0)
    }
}