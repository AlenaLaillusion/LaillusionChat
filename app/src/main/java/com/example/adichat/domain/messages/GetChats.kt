package com.example.adichat.domain.messages

import com.example.adichat.domain.interactor.UseCase
import javax.inject.Inject

class GetChats @Inject constructor(
    private val messagesRepository: MessagesRepository
) : UseCase<List<MessageEntity>, GetChats.Params>() {

    override suspend fun run(params: Params) = messagesRepository.getChats(params.needFetch)

    data class Params (val needFetch: Boolean)
}