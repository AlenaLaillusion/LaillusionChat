package com.example.adichat.presentation

import com.example.adichat.domain.account.CheckAuth
import com.example.adichat.domain.type.None
import javax.inject.Inject
import javax.inject.Singleton

@Singleton //класс для проверки авторизации пользователя
class Authenticator @Inject constructor(
    val checkAuth: CheckAuth
) {
    fun userLoggedIn(body: (Boolean) -> Unit) {
        checkAuth(None()) {
            it.either({ body(false)}, { body(it)})
        }
    }//проверяет наличие сохр. акк
}