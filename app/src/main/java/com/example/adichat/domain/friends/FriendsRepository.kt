package com.example.adichat.domain.friends

import com.example.adichat.domain.type.Either
import com.example.adichat.domain.type.Failure
import com.example.adichat.domain.type.None

interface FriendsRepository {
    fun getFriends(needFetch: Boolean): Either<Failure, List<FriendEntity>>
    fun getFriendRequests(needFetch: Boolean): Either<Failure, List<FriendEntity>>

    fun approveFriendRequest(friendEntity: FriendEntity): Either<Failure, None>
    fun cancelFriendRequest(friendEntity: FriendEntity): Either<Failure, None>

    fun addFriend(email: String): Either<Failure, None>
    fun deleteFriend(friendEntity: FriendEntity): Either<Failure, None>
}