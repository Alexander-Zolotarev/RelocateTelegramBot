package ru.zolotarev.relocate.telegram

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update
import ru.zolotarev.relocate.config.BotConfig
import ru.zolotarev.relocate.model.Command
import ru.zolotarev.relocate.model.KeyboardButton
import ru.zolotarev.relocate.service.CountryService
import ru.zolotarev.relocate.service.StartService
import ru.zolotarev.relocate.service.TestService

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
            logger.info("update is: $update")
            val message = update.message

            if (message.hasText() && message.hasEntities()) {
                when (message.text) {
                    Command.START.value -> sendMessage(
                        startService.startCommandProcessing(update).getOrElse { throw IllegalStateException() })

                    Command.COUNTRY.value -> sendMessage(
                        countryService.countryCommandProcessing(update).getOrElse { throw IllegalStateException() })

                    Command.TEST.value -> sendMessage(
                        testService.testCommandProcessing(update).getOrElse { throw IllegalStateException() })
                }
            } else {
                when(message.text) {
                    KeyboardButton.COUNTRY.description -> sendMessage(
                        countryService.countryCommandProcessing(update).getOrElse { throw IllegalStateException() })
                }
            }
        }
    }

    private fun sendMessage(sendMessage: SendMessage) {
        try {
            logger.info(sendMessage.toString())
            execute(sendMessage)
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