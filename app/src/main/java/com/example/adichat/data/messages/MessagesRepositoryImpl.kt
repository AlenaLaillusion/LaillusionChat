package com.example.adichat.data.messages

import com.example.adichat.data.account.AccountCache
import com.example.adichat.domain.messages.MessageEntity
import com.example.adichat.domain.messages.MessagesRepository
import com.example.adichat.domain.type.*

class MessagesRepositoryImpl(
    private val messagesRemote: MessagesRemote,
    private val messagesCashe: MessagesCashe,
    private val accountCache: AccountCache
) : MessagesRepository {

    override fun sendMessage(toId: Long, message: String, image: String): Either<Failure, None> {
        return accountCache.getCurrentAccount().flatMap {
            messagesRemote.sendMessage(it.id, toId, it.token, message, image)
        }
    }

    override fun getChats(needFetch: Boolean): Either<Failure, List<MessageEntity>> {
        return accountCache.getCurrentAccount().flatMap { account ->
            return@flatMap if(needFetch) {
                messagesRemote.getChats(account.id, account.token).onNext {
                    it.map { message ->
                        if (message.senderId == account.id) {
                            message.fromMe = true
                        }
                        messagesCashe.saveMessage(message)
                    }
                    messagesCashe.saveMessages(it)
                }
            } else {
                Either.Right(messagesCashe.getChats())
            }
        }
            .map { it.distinctBy {
                it.contact?.id
            } }
    }

    override fun getMessagesWithContact(contactId: Long, needFetch: Boolean): Either<Failure, List<MessageEntity>> {
        return accountCache.getCurrentAccount().flatMap { account ->
            return@flatMap if (needFetch) {
                messagesRemote.getMessagesWithContact(contactId, account.id, account.token).onNext {
                    it.map { message ->
                        if (message.senderId == account.id) {
                            message.fromMe = true
                        }
                        val contact = messagesCashe.getChats().firstOrNull(){ it.contact?.id == contactId}?.contact
                        message.contact = contact

                    }
                    messagesCashe.saveMessages(it)
                }
            } else {
                Either.Right(messagesCashe.getMessagesWithContact(contactId))
            }
        }
    }

    override fun deleteMessagesByUser(messageId: Long): Either<Failure, None> {
        return accountCache.getCurrentAccount().flatMap {
            messagesCashe.deleteMessagesByUser(messageId)
            messagesRemote.deleteMessagesByUser(it.id, messageId, it.token)
        }
    }
}