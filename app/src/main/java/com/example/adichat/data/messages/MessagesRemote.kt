package com.example.adichat.data.messages

import com.example.adichat.domain.messages.MessageEntity
import com.example.adichat.domain.type.Either
import com.example.adichat.domain.type.Failure
import com.example.adichat.domain.type.None

interface MessagesRemote {
    fun getChats(userId: Long, token: String): Either<Failure, List<MessageEntity>>

    fun getMessagesWithContact(contactId: Long, userId: Long, token: String): Either<Failure, List<MessageEntity>>

    fun sendMessage(
        fromId: Long,
        toId: Long,
        token: String,
        message:String,
        image: String
    ): Either<Failure, None>

    fun deleteMessagesByUser(userId: Long, messageId: Long, token: String): Either<Failure, None>
}