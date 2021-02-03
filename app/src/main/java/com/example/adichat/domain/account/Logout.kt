package com.example.adichat.domain.account

import com.example.adichat.domain.interactor.UseCase
import com.example.adichat.domain.type.Either
import com.example.adichat.domain.type.Failure
import com.example.adichat.domain.type.None
import javax.inject.Inject

class Logout @Inject constructor(
    private val accountRepository: AccountRepository
) : UseCase<None, None>() {

    override suspend fun run(params: None): Either<Failure, None> = accountRepository.logout()

}