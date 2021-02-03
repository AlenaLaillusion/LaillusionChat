package com.example.adichat.data.account

import com.example.adichat.domain.account.AccountEntity
import com.example.adichat.domain.type.Either
import com.example.adichat.domain.type.Failure
import com.example.adichat.domain.type.None

interface AccountRemote {
    fun register(
        email: String,
        name: String,
        password: String,
        token: String,
        userDate: Long
    ): Either<Failure, None>

    fun login(email: String, password: String, token: String): Either<Failure, AccountEntity> //выполняет авторизацию
    fun updateToken(userId: Long, token: String, oldToken: String): Either<Failure, None> //выполняет обоновление токена на сервере

    fun editUser(
        userId: Long,
        email: String,
        name: String,
        password: String,
        status: String,
        token: String,
        image: String
    ): Either<Failure, AccountEntity>  //меняет данные юзера на сервере

    fun updateAccountLastSeen(userId: Long, token: String, lastSeen: Long): Either<Failure, None>
    fun forgetPassword(email: String): Either<Failure, None>
}