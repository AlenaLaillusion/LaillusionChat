package com.example.adichat.remote.friends

import com.example.adichat.domain.friends.FriendEntity
import com.example.adichat.remote.core.BaseResponse


class GetFriendsResponse (
    success: Int,
    message: String,
    val friends: List<FriendEntity>
) : BaseResponse(success, message)