package com.example.adichat.remote.friends

import com.example.adichat.domain.friends.FriendEntity
import com.example.adichat.remote.core.BaseResponse
import com.google.gson.annotations.SerializedName

class GetFriendRequestsResponse (
    success: Int,
    message: String,
    @SerializedName("friend_requests")
    val friendsRequests: List<FriendEntity>
) : BaseResponse(success, message)