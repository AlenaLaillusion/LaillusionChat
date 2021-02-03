package com.example.adichat.domain.friends

import com.example.adichat.domain.interactor.UseCase
import com.example.adichat.domain.type.None
import javax.inject.Inject

class CancelFriendRequest @Inject constructor(
    private val friendsRepository: FriendsRepository
) : UseCase<None, FriendEntity>() {

    override suspend fun run(params: FriendEntity) = friendsRepository.cancelFriendRequest(params)
}