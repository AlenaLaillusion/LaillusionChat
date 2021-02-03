package com.example.adichat.remote.messages

import com.example.adichat.domain.messages.MessageEntity
import com.example.adichat.remote.core.BaseResponse

class GetMessagesResponse (
    success: Int,
    message: String,
    val messages: List<MessageEntity>
) : BaseResponse(success, message)