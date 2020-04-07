package com.example.adichat.data.account

import com.example.adichat.domain.type.Either
import com.example.adichat.domain.type.None
import com.example.adichat.domain.type.exception.Failure

interface AccountCache {
    fun getToken(): Either<Failure, String>
    fun saveToken(token: String): Either<Failure, None>
}