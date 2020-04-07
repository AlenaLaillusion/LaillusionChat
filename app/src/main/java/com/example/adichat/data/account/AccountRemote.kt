package com.example.adichat.data.account

import com.example.adichat.domain.type.Either
import com.example.adichat.domain.type.None
import com.example.adichat.domain.type.exception.Failure

interface AccountRemote {
    fun register(
        email: String,
        name: String,
        password: String,
        token: String,
        userDate: Long
    ): Either<Failure, None>
}