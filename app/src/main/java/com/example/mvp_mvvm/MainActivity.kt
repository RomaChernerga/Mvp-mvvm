package com.example.mvp_mvvm

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.mvp_mvvm.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity(), LoginContract.View {

    private lateinit var binding: ActivityMainBinding
    private var presenter: LoginContract.Presenter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        presenter = getPresenter()
        presenter?.onAttach(this)

        binding.btnEnter.setOnClickListener {
            presenter?.onLogin(
                binding.edTextLogin.text.toString(),
                binding.edTextPass.text.toString()
            )
        }

    }

    private fun getPresenter(): LoginPresenter? {
        val presenter = lastCustomNonConfigurationInstance as? LoginPresenter
        return presenter ?: LoginPresenter()
    }

    override fun onRetainCustomNonConfigurationInstance(): Any? {

        return presenter
    }


    private fun hideKeyboard(activity: Activity) {
        val imm = activity.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        //Find the currently focused view, so we can grab the correct window token from it.
        var view = activity.currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    override fun setSuccess() {
        binding.btnEnter.isVisible = false
        binding.edTextLogin.isVisible = false
        binding.edTextPass.isVisible = false
        binding.root.setBackgroundColor(Color.GREEN)
    }

    override fun setError(error: String) {
        Snackbar.make(binding.root, error, Snackbar.LENGTH_SHORT).show()
    }

    override fun showProgress() {
        binding.btnEnter.isEnabled = false
        hideKeyboard(this)
    }

    override fun hideProgress() {
        binding.btnEnter.isEnabled = true
    }

}