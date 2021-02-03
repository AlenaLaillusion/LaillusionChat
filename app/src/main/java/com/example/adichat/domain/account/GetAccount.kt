package com.example.adichat.domain.account

import com.example.adichat.domain.interactor.UseCase
import com.example.adichat.domain.type.None
import javax.inject.Inject

class GetAccount @Inject constructor(
    private val accountRepository: AccountRepository
) : UseCase<AccountEntity, None>() {
    override suspend fun run(params: None) = accountRepository.getCurrentAccount()
}