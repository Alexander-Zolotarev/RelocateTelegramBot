package ru.zolotarev.relocate.utils

import org.telegram.telegrambots.meta.api.objects.Update

object BotUtils {
    fun getChatId(update: Update) : String {
        return update.message.chatId.toString()
    }
}