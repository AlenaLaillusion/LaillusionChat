package com.example.adichat.domain.account

import com.example.adichat.domain.type.None
import com.example.adichat.domain.type.Either
import com.example.adichat.domain.type.exception.Failure
import com.example.adichat.domain.interactor.UseCase
import javax.inject.Inject

class Register @Inject constructor(
    private val repository: AccountRepository
) : UseCase<None, Register.Params>() {

    override suspend fun run(params: Params): Either<Failure, None> {
        return repository.register(params.email, params.name, params.password)
    }

    data class Params(val email: String, val name: String, val password: String)
}

