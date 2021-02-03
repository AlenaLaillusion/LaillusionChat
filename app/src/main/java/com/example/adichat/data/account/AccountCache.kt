package com.example.adichat.data.account

import com.example.adichat.domain.account.AccountEntity
import com.example.adichat.domain.type.Either
import com.example.adichat.domain.type.Failure
import com.example.adichat.domain.type.None

interface AccountCache {
    fun getToken(): Either<Failure, String>
    fun saveToken(token: String): Either<Failure, None>

    fun logout(): Either<Failure, None>  //выполняет выход

    fun getCurrentAccount(): Either<Failure, AccountEntity> //получает текущий акк
    fun saveAccount(account: AccountEntity): Either<Failure, None> //сохраняет тек акк в бд

    fun checkAuth(): Either<Failure, Boolean>
}