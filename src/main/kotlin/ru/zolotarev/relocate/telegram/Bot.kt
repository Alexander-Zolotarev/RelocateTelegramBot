package ru.zolotarev.relocate.telegram

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.objects.Update

@Component
class Bot : TelegramLongPollingBot() {
    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    lateinit var token: String
    lateinit var username: String



    override fun onUpdateReceived(update: Update) {
        TODO("Not yet implemented")
    }

    override fun getBotToken(): String {
        return token
    }

    override fun getBotUsername(): String {
        return username
    }
}