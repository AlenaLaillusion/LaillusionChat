package com.example.adichat.presentation.injection

import android.content.Context
import com.example.adichat.data.account.AccountCache
import com.example.adichat.data.account.AccountRemote
import com.example.adichat.data.account.AccountRepositoryImpl
import com.example.adichat.domain.account.AccountRepository
import dagger.Module
import dagger.Provides
import java.security.AccessControlContext
import javax.inject.Singleton

@Module
class AppModule(private val context: Context) {

    @Provides
    @Singleton
    fun provideAppContext(): Context = context

    @Provides
    @Singleton
    fun provideAccountRepository(remote: AccountRemote, cache: AccountCache) : AccountRepository {
        return AccountRepositoryImpl(remote, cache)
    }

}