package com.example.adichat.presentation.injection

import android.content.Context
import android.content.SharedPreferences
import com.example.adichat.cache.AccountCacheImpl
import com.example.adichat.cache.SharedPrefsManager
import com.example.adichat.data.account.AccountCache
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CacheModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(context: Context) : SharedPreferences {
        return context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideAccountCache(prefsManager: SharedPrefsManager) : AccountCache = AccountCacheImpl(prefsManager)
}