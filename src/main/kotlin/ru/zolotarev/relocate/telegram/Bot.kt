package ru.zolotarev.relocate.telegram

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update
import ru.zolotarev.relocate.config.BotConfig
import ru.zolotarev.relocate.model.Command

@Component
class Bot(
    botConfig: BotConfig
) : TelegramLongPollingBot() {
    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    private val token: String = botConfig.token
    private val username: String = botConfig.username


    override fun onUpdateReceived(update: Update) {
        if (update.hasMessage() && update.message.hasText()) {
            val message = update.message
            val text = message.text
            val chatId = update.message.chatId

            when (text) {
                Command.START.value -> startCommandReceived(chatId.toString(), message.chat.userName)
            }
        }
    }

    private fun startCommandReceived(chatId: String, userName: String) {
        val textToSend = "Hi, $userName, nice to meet you!"
        sendMessage(chatId, textToSend)
    }

    private fun sendMessage(chatId: String, textToSend: String) {
        val message = SendMessage(chatId, textToSend)

        try {
            execute(message)
        } catch (e: Exception) {
            logger.error("Can`t send message to user!")
        }
    }

    override fun getBotToken(): String {
        return token
    }

    override fun getBotUsername(): String {
        return username
    }
}