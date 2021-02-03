package com.example.adichat.domain.messages

import com.example.adichat.domain.interactor.UseCase
import com.example.adichat.domain.type.None
import javax.inject.Inject

class DeleteMessage @Inject constructor(
    private val messgesRepository: MessagesRepository
) : UseCase<None, DeleteMessage.Params>() {

    override suspend fun run(params: Params) = messgesRepository.deleteMessagesByUser(params.messageId)

    data class Params (val messageId: Long)
}