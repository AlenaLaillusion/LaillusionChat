package com.example.adichat.remote.account


import android.util.Log
import com.example.adichat.data.account.AccountRemote
import com.example.adichat.domain.account.AccountEntity
import com.example.adichat.domain.type.Either
import com.example.adichat.domain.type.Failure
import com.example.adichat.domain.type.None
import com.example.adichat.remote.core.Request
import com.example.adichat.remote.service.ApiService
import java.util.*
import javax.inject.Inject
import kotlin.collections.HashMap

class AccountRemoteImpl @Inject constructor(
    private val request: Request,
    private val service: ApiService
) : AccountRemote {

    override fun register(email: String, name: String, password: String, token: String, userDate: Long
    ): Either<Failure, None> {
        return request.make(service.register(createRegisterMap(email, name, password, token, userDate))) { None() }
    }

    override fun login(email: String, password: String, token: String //выполняет авторизацию
    ): Either<Failure, AccountEntity> {
        return request.make(service.login(createLoginMap(email, password, token))) {it.user}
    }

    override fun forgetPassword(email: String): Either<Failure, None> {
        val map = HashMap<String, String>().apply {
            put(ApiService.PARAM_EMAIL, email)
        }
        return request.make(service.forgetPassword(map)) { None() }
    }

    override fun updateToken(userId: Long, token: String, oldToken: String): Either<Failure, None> { //выполняет обновление токена
        return request.make(service.updateToken(createUpdateTokenMap(userId, token, oldToken))) { None() }
    }

    override fun editUser(
        userId: Long,
        email: String,
        name: String,
        password: String,
        status: String,
        token: String,
        image: String
    ): Either<Failure, AccountEntity> {
        return request.make(service.editUser(createUserEditMap(userId, email, name, password,
            status, token, image))) { it.user }
    }

    override fun updateAccountLastSeen(
        userId: Long,
        token: String,
        lastSeen: Long
    ): Either<Failure, None> {
        return request.make(service.updateUserLastSeen(createUpdateLastSeenMap(userId, token, lastSeen))) { None() }
    }

    private fun createUserEditMap(
        id: Long,
        email: String,
        name: String,
        password: String,
        status: String,
        token: String,
        image: String
    ): Map<String, String> {
        val map = HashMap<String, String>()
        map.put(ApiService.PARAM_USER_ID, id.toString())
        map.put(ApiService.PARAM_EMAIL, email)
        map.put(ApiService.PARAM_NAME, name)
        map.put(ApiService.PARAM_PASSWORD, password)
        map.put(ApiService.PARAM_STATUS, status)
        map.put(ApiService.PARAM_TOKEN, token)
        if (image.startsWith("../")) {
            map.put(ApiService.PARAM_IMAGE_UPLOADED, image)
        } else {
            map.put(ApiService.PARAM_IMAGE_NEW, image)
            map.put(ApiService.PARAM_IMAGE_NEW_NAME, "user_${id}_${Date().time}_photo")
        }
        return map
    }

    private fun createUpdateLastSeenMap(
        userId: Long,
        token: String,
        lastSeen: Long
    ): Map<String, String> {
        val map = HashMap<String, String>()
        map.put(ApiService.PARAM_USER_ID, userId.toString())
        map.put(ApiService.PARAM_TOKEN, token)
        map.put(ApiService.PARAM_LAST_SEEN, lastSeen.toString())
        return map
    }

    }

    private fun createRegisterMap(email: String, name: String, password: String, token: String, userDate: Long
    ): Map<String, String> {
        val map = HashMap<String, String>()
        map.put(ApiService.PARAM_EMAIL, email)
        map.put(ApiService.PARAM_NAME, name)
        map.put(ApiService.PARAM_PASSWORD, password)
        map.put(ApiService.PARAM_TOKEN, token)
        map.put(ApiService.PARAM_USER_DATE, userDate.toString())
        return map
    }

    private fun createLoginMap(email: String, password: String, token: String): Map<String, String> {//вспом ф.создающая мап для авторизации
        val map = HashMap<String, String>()
        map.put(ApiService.PARAM_EMAIL, email)
        map.put(ApiService.PARAM_PASSWORD, password)
        map.put(ApiService.PARAM_TOKEN, token)
        return map
    }

    private fun createUpdateTokenMap(userId: Long, token: String, oldToken: String): Map<String, String> {//вспои.ф создающая мап для обновл токена
        val map = HashMap<String, String>()
        map.put(ApiService.PARAM_USER_ID, userId.toString())
        map.put(ApiService.PARAM_TOKEN, token)
        map.put(ApiService.PARAM_OLD_TOKEN, oldToken)
        Log.d("createUpdateTokenMap", " userId = " + userId + " token= "  + token)
        return map
    }
