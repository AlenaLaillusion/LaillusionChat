package com.example.adichat.cache

import com.example.adichat.data.account.AccountCache
import com.example.adichat.domain.account.AccountEntity
import com.example.adichat.domain.type.Either
import com.example.adichat.domain.type.Failure
import com.example.adichat.domain.type.None
import javax.inject.Inject

class AccountCacheImpl @Inject constructor(private val prefsManager: SharedPrefsManager, private val chatDatabase: ChatDatabase) : AccountCache {

    override fun saveToken(token: String): Either<Failure, None> {
        return prefsManager.saveToken(token)
    }

    override fun getToken(): Either<Failure, String> {
        return prefsManager.getToken()
    }

    override fun logout(): Either<Failure, None> { //удаляет данный акк из БД
        chatDatabase.clearAllTables()
        return prefsManager.removeAccoount()
    }

    override fun getCurrentAccount(): Either<Failure, AccountEntity> { //получает тек.акк из БД
        return prefsManager.getAccount()
    }

    override fun saveAccount(account: AccountEntity): Either<Failure, None> { //сохраняет акк в БД
        return prefsManager.saveAccount(account)
    }

    override fun checkAuth(): Either<Failure, Boolean> {
        return prefsManager.containsAnyAccount()
    }
}