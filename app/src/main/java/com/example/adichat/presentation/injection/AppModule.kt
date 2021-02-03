package com.example.adichat.presentation.injection

import android.content.Context
import com.example.adichat.data.account.AccountCache
import com.example.adichat.data.account.AccountRemote
import com.example.adichat.data.account.AccountRepositoryImpl
import com.example.adichat.data.friends.FriendsRemote
import com.example.adichat.data.friends.FriendsRepositoryImpl
import com.example.adichat.data.media.MediaRepositoryImpl
import com.example.adichat.data.messages.MessagesCashe
import com.example.adichat.data.messages.MessagesRemote
import com.example.adichat.data.messages.MessagesRepositoryImpl
import com.example.adichat.domain.account.AccountRepository
import com.example.adichat.domain.friends.FriendsCache
import com.example.adichat.domain.friends.FriendsRepository
import com.example.adichat.domain.media.MediaRepository
import com.example.adichat.domain.messages.MessagesRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val context: Context) {

    @Provides
    @Singleton
    fun provideAppContext(): Context = context

    @Provides
    @Singleton
    fun provideAccountRepository(remote: AccountRemote, cache: AccountCache): AccountRepository {
        return AccountRepositoryImpl(remote, cache)
    }

    @Provides
    @Singleton
    fun provideFriendsRepository(remote: FriendsRemote, accountCache: AccountCache, friendsCache: FriendsCache): FriendsRepository {
        return FriendsRepositoryImpl(accountCache, remote, friendsCache)
    }

    @Provides
    @Singleton
    fun provideMediaRepository(context: Context): MediaRepository {
        return MediaRepositoryImpl(context)
    }

    @Provides
    @Singleton
    fun provideMessagesRepository(remote: MessagesRemote, cache: MessagesCashe, accountCache: AccountCache): MessagesRepository {
        return MessagesRepositoryImpl(remote, cache, accountCache)
    }

}