package com.example.adichat.domain.friends

import com.example.adichat.domain.interactor.UseCase
import com.example.adichat.domain.type.None
import javax.inject.Inject

class AddFriend @Inject constructor(
    private val friendsRepository: FriendsRepository
) : UseCase<None, AddFriend.Params>() {

    override  suspend fun run(params: Params) = friendsRepository.addFriend(params.email)

    data class Params(val email: String)
}