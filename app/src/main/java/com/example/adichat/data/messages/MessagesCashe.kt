package com.example.adichat.data.messages

import com.example.adichat.domain.messages.MessageEntity

interface MessagesCashe {
    fun saveMessage(entity: MessageEntity)

    fun getChats(): List<MessageEntity>

    fun getMessagesWithContact(contactId: Long): List<MessageEntity>

    fun saveMessages(entities: List<MessageEntity>)

    fun deleteMessagesByUser(messageId: Long)
}