package ru.zolotarev.relocate.telegram

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup
import ru.zolotarev.relocate.config.BotConfig
import ru.zolotarev.relocate.model.Command
import ru.zolotarev.relocate.service.CountryService
import ru.zolotarev.relocate.service.StartService
import ru.zolotarev.relocate.service.TestService
import ru.zolotarev.relocate.utils.BotUtils.getChatId

@Component
class Bot(
    botConfig: BotConfig,
    private val startService: StartService,
    private val countryService: CountryService,
    private val testService: TestService,
) : TelegramLongPollingBot() {
    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    private val token: String = botConfig.token
    private val username: String = botConfig.username

    override fun onUpdateReceived(update: Update) {
        if (update.hasMessage() && update.message.hasText()) {
            println("1")
            logger.info("update is: $update")
            val message = update.message

            when (message.text) {
                Command.START.value -> {
                    val startCommandProcessing = startService.startCommandProcessing(update)
                    sendMessage(getChatId(update), startCommandProcessing.get().keyboardMarkup)
                }
                Command.COUNTRY.value -> countryService.countryCommandProcessing(update)
                Command.TEST.value -> testService.testCommandProcessing(update)
            }
        }
    }

    private fun sendMessage(chatId: String, replyKeyboard: ReplyKeyboardMarkup) {
        val message = SendMessage()
        message.chatId = chatId
        message.replyMarkup = replyKeyboard
        message.text = "Выберите действие"
        try {
            println("3")
            println("message:$message")
            execute(message)
        } catch (e: Exception) {
            logger.error("Can`t send message to user!")
        }
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