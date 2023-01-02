package ru.zolotarev.relocate.utils

import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.CallbackQuery
import org.telegram.telegrambots.meta.api.objects.Update

object BotUtils {
    fun chatId(update: Update): String {
        return if (update.message != null) {
            update.message.chatId.toString()
        } else {
            update.callbackQuery.message.chatId.toString()
        }
    }

    fun CallbackQuery.chatId(): String {
        return this.message.chatId.toString()
    }

    fun Update.sendMessageWithWaitText(): SendMessage {
        val sendMessage = SendMessage()
        sendMessage.chatId = chatId(this)
        sendMessage.text = "Данный раздел в процессе заполнения"
        return sendMessage
    }


}