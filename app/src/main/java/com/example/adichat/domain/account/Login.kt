package com.example.adichat.domain.account

import com.example.adichat.domain.interactor.UseCase
import javax.inject.Inject

class Login @Inject constructor(
    private val accountRepository: AccountRepository
) : UseCase<AccountEntity, Login.Params>() {

    override suspend fun run(params: Params) = accountRepository.login(params.email, params.password)

    data class Params(val email: String, val password: String)
}