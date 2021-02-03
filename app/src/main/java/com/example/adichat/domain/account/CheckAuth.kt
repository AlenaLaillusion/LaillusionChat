package com.example.adichat.domain.account

import com.example.adichat.domain.interactor.UseCase
import com.example.adichat.domain.type.Either
import com.example.adichat.domain.type.Failure
import com.example.adichat.domain.type.None
import javax.inject.Inject

class CheckAuth @Inject constructor(
    private val accountRepository: AccountRepository
) : UseCase<Boolean, None>() {

    override suspend fun run(params: None): Either<Failure, Boolean> = accountRepository.checkAuth()
}