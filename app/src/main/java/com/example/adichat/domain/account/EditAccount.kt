package com.example.adichat.domain.account

import com.example.adichat.domain.interactor.UseCase
import com.example.adichat.domain.type.Either
import com.example.adichat.domain.type.Failure
import javax.inject.Inject

class EditAccount @Inject constructor(
    private val repository: AccountRepository
) : UseCase<AccountEntity, AccountEntity>() {

    override suspend fun run(params: AccountEntity): Either<Failure, AccountEntity> {
        return repository.editAccount(params)
    }
}