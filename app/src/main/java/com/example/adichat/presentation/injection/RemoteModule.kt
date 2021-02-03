package com.example.adichat.presentation.injection

import com.andreabaccega.formedittext.BuildConfig
import com.example.adichat.data.account.AccountRemote
import com.example.adichat.data.friends.FriendsRemote
import com.example.adichat.data.messages.MessagesRemote
import com.example.adichat.remote.account.AccountRemoteImpl
import com.example.adichat.remote.core.Request
import com.example.adichat.remote.friends.FriendsRemoteImpl
import com.example.adichat.remote.messages.MessagesRemoteImpl
import com.example.adichat.remote.service.ApiService
import com.example.adichat.remote.service.ServiceFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RemoteModule {

    @Singleton
    @Provides
    fun provideApiService(): ApiService = ServiceFactory.makeService(BuildConfig.DEBUG)

    @Singleton
    @Provides
    fun provideAccountRemote(request: Request, apiService: ApiService): AccountRemote {
        return AccountRemoteImpl(request, apiService)
    }

    @Singleton
    @Provides
    fun provideFriendsRemote(request: Request, apiService: ApiService): FriendsRemote {
        return FriendsRemoteImpl(request, apiService)
    }

    @Singleton
    @Provides
    fun provideMessagesRemote(request: Request, apiService: ApiService): MessagesRemote {
        return MessagesRemoteImpl(request, apiService)
    }
}