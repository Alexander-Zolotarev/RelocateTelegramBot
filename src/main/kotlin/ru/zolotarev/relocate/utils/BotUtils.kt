package ru.zolotarev.relocate.utils

import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update

object BotUtils {
    fun chatId(update: Update) : String {
        return update.message.chatId.toString()
    }

    fun Update.sendMessageWithWaitText() : SendMessage {
        val sendMessage = SendMessage()
        sendMessage.chatId = this.message.chatId.toString()
        sendMessage.text = "Данный раздел в процессе заполнения"
        return sendMessage
    }


}