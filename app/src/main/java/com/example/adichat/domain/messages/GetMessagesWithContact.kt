package com.example.adichat.domain.messages

import com.example.adichat.domain.interactor.UseCase
import javax.inject.Inject

class GetMessagesWithContact @Inject constructor(
    private val messagesRepository: MessagesRepository
) : UseCase<List<MessageEntity>, GetMessagesWithContact.Params>() {

    override suspend fun run(params: Params) = messagesRepository.getMessagesWithContact(params.contactId, params.needFetch)

    data class Params(val contactId: Long, val needFetch: Boolean)
}
