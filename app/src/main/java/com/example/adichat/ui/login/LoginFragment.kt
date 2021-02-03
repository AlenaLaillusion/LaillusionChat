package com.example.adichat.ui.login

import android.os.Bundle
import android.view.View
import com.example.adichat.R
import com.example.adichat.domain.account.AccountEntity
import com.example.adichat.presentation.viewmodel.AccountViewModel
import com.example.adichat.ui.App
import com.example.adichat.ui.core.BaseFragment
import com.example.adichat.ui.core.ext.onFailure
import com.example.adichat.ui.core.ext.onSuccess
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : BaseFragment() {
    override val layoutId: Int = R.layout.fragment_login
    override val titleToolbar = R.string.screen_login

    private lateinit var accountViewModel: AccountViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)


        accountViewModel = viewModel {
            onSuccess(accountData, ::renderAccount)
            onFailure(failureData, ::handleFailure)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnLogin.setOnClickListener {
            validateFields()
        }

        btnRegister.setOnClickListener {
            activity?.let { navigator.showSignUp(it) }
        }

        btnForgetPassword.setOnClickListener {
            activity?.let{ navigator.showForgedPassword(it)}
        }
    }

    private fun validateFields() {
        hideSoftKeyboard()
        val allFields = arrayOf(etEmail, etPassword)
        var allValid = true
        for (field in allFields) {
            allValid = field.testValidity() && allValid
        }
        if (allValid) {
            login(etEmail.text.toString(), etPassword.text.toString())
        }
    }

    private fun login(email: String, password: String) {
        showProgress()
        accountViewModel.login(email, password)
    }

    private fun renderAccount(account: AccountEntity?) {
        hideProgress()
        activity?.let {
            navigator.showHome(it)
            it.finish()
        }
    }
}
