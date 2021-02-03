package com.example.adichat.domain.friends

interface FriendsCache {
    fun saveFriend(entity: FriendEntity)

    fun getFriend(key: Long): FriendEntity?

    fun getFriends(): List<FriendEntity>

    fun getFriendRequests(): List<FriendEntity>

    fun removeFriendEntity(key: Long)
}