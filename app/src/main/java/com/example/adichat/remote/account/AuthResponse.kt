package com.example.adichat.remote.account

import com.example.adichat.domain.account.AccountEntity
import com.example.adichat.remote.core.BaseResponse

class AuthResponse( //POJO класс для хранения отвера сервера при авторизации
    success: Int,
    message: String,
    val user: AccountEntity
) : BaseResponse(success, message)