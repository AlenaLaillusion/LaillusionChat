package com.example.adichat.cache

import com.example.adichat.data.account.AccountCache
import com.example.adichat.domain.type.Either
import com.example.adichat.domain.type.None
import com.example.adichat.domain.type.exception.Failure
import javax.inject.Inject

class AccountCacheImpl @Inject constructor(private val prefsManager: SharedPrefsManager) : AccountCache {

    override fun saveToken(token: String): Either<Failure, None> {
        return prefsManager.saveToken(token)
    }

    override fun getToken(): Either<Failure, String> {
        return prefsManager.getToken()
    }
}