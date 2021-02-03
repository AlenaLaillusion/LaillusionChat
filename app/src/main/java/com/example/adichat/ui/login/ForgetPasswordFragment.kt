package com.example.adichat.ui.login

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.adichat.R
import com.example.adichat.domain.type.None
import com.example.adichat.presentation.viewmodel.AccountViewModel
import com.example.adichat.ui.App
import com.example.adichat.ui.core.BaseFragment
import com.example.adichat.ui.core.ext.onFailure
import com.example.adichat.ui.core.ext.onSuccess
import kotlinx.android.synthetic.main.fragment_forget_password.*

class ForgetPasswordFragment : BaseFragment() {
    override val layoutId: Int = R.layout.fragment_forget_password
    override val titleToolbar: Int = R.string.screen_forget_password

    lateinit var accountViewModel: AccountViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        accountViewModel = viewModel {
            onSuccess(forgetPasswordData, ::onPasswordSent)
            onFailure(failureData, ::handleFailure)
        }

        btnSendPassword.setOnClickListener {
            sendPassword()
        }
    }

    private fun sendPassword() {
        if(etEmail.testValidity()) {
            val email = etEmail.text.toString()
            accountViewModel.forgetPassword(email)
        }
    }

    private fun onPasswordSent(none: None?) {
        Toast.makeText(requireContext(), getString(R.string.email_sent), Toast.LENGTH_SHORT).show()
        activity?.finish()
    }
}