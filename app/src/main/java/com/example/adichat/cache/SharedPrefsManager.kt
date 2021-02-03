package com.example.adichat.cache

import android.content.SharedPreferences
import com.example.adichat.domain.account.AccountEntity
import com.example.adichat.domain.type.Either
import com.example.adichat.domain.type.Failure
import com.example.adichat.domain.type.None
import javax.inject.Inject

class SharedPrefsManager @Inject constructor(private val prefs: SharedPreferences) {
    companion object {
        const val ACCOUNT_TOKEN = "account_token"
        const val ACCOUNT_ID = "account_id"
        const val ACCOUNT_NAME = "account_name"
        const val ACCOUNT_EMAIL = "account_email"
        const val ACCOUNT_STATUS = "account_status"
        const val ACCOUNT_DATE = "account_date"
        const val ACCOUNT_IMAGE = "account_image"
        const val ACCOUNT_PASSWORD = "account_password"
    }

    fun saveToken(token: String): Either<Failure, None> {
        prefs.edit().apply {
            putString(ACCOUNT_TOKEN, token)
        }.apply()

        return Either.Right(None())
    }

    fun getToken(): Either<Failure, String> {
        return Either.Right(prefs.getString(ACCOUNT_TOKEN, ""))
    }


    fun saveAccount(account: AccountEntity): Either<Failure, None> { //сохраняет поля акк
        prefs.edit().apply {
            putSafely(ACCOUNT_ID, account.id)
            putSafely(ACCOUNT_NAME, account.name)
            putSafely(ACCOUNT_EMAIL, account.email)
            putSafely(ACCOUNT_TOKEN, account.token)
            putString(ACCOUNT_STATUS, account.status)
            putSafely(ACCOUNT_DATE, account.userDate)
            putSafely(ACCOUNT_IMAGE, account.image)
            putSafely(ACCOUNT_PASSWORD, account.password)
        }.apply()
        return Either.Right(None())
    }

    fun getAccount(): Either<Failure, AccountEntity> { //проверяет наличие акк. в SharedPreferences
        val id = prefs.getLong(ACCOUNT_ID, 0)

        if (id == 0L) {
            return Either.Left(Failure.NoSavedAccountError)
        }
        val account = AccountEntity(
            prefs.getLong(ACCOUNT_ID, 0),
            prefs.getString(ACCOUNT_NAME, ""),
            prefs.getString(ACCOUNT_EMAIL, ""),
            prefs.getString(ACCOUNT_TOKEN, ""),
            prefs.getString(ACCOUNT_STATUS, ""),
            prefs.getLong(ACCOUNT_DATE, 0),
            prefs.getString(ACCOUNT_IMAGE, ""),
            prefs.getString(ACCOUNT_PASSWORD, "")
        )
        return Either.Right(account)
    }

    fun removeAccoount(): Either<Failure, None> { // удаляет акк
        prefs.edit().apply {
            remove(ACCOUNT_ID)
            remove(ACCOUNT_NAME)
            remove(ACCOUNT_EMAIL)
            remove(ACCOUNT_STATUS)
            remove(ACCOUNT_DATE)
            remove(ACCOUNT_IMAGE)
        }.apply()
        return Either.Right(None())
    }

    fun containsAnyAccount(): Either<Failure, Boolean> { //проверяет наличие акк
        val id = prefs.getLong(ACCOUNT_ID, 0)
        return Either.Right(id != 0L)
    }

    fun SharedPreferences.Editor.putSafely(key: String, value: Long?) {
        if (value != null && value != 0L) {
            putLong(key, value)
        }
    }

    fun SharedPreferences.Editor.putSafely(key: String, value: String?) {
        if (value != null && value.isNotEmpty()) {
            putString(key, value)
        }
    }
}