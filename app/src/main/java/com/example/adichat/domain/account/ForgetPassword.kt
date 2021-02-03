package com.example.adichat.domain.account

import com.example.adichat.domain.interactor.UseCase
import com.example.adichat.domain.type.None
import javax.inject.Inject

class ForgetPassword @Inject constructor(
    private val accountRepository: AccountRepository
) : UseCase<None, ForgetPassword.Params>(){

    override suspend fun run(params: Params) = accountRepository.forgetPassword(params.email)

   data class Params(val email: String)
}