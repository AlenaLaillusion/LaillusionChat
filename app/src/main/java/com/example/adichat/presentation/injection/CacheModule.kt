package com.example.adichat.presentation.injection

import android.content.Context
import android.content.SharedPreferences
import com.example.adichat.cache.AccountCacheImpl
import com.example.adichat.cache.ChatDatabase
import com.example.adichat.cache.SharedPrefsManager
import com.example.adichat.data.account.AccountCache
import com.example.adichat.data.messages.MessagesCashe
import com.example.adichat.domain.friends.FriendsCache
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
    fun provideAccountCache(prefsManager: SharedPrefsManager, chatDatabase: ChatDatabase): AccountCache = AccountCacheImpl(prefsManager, chatDatabase)

    @Provides
    @Singleton
    fun provideChatDatabase(context: Context): ChatDatabase {
        return ChatDatabase.getInstance(context)
    }

    @Provides
    @Singleton
    fun provideFriendsCache(chatDatabase: ChatDatabase): FriendsCache {
        return chatDatabase.friendsDao
    }

    @Provides
    @Singleton
    fun provideMessagesCache(chatDatabase: ChatDatabase): MessagesCashe {
        return chatDatabase.messagesDao
    }
}