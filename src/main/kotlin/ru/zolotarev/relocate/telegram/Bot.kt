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
import ru.zolotarev.relocate.service.CallbackHandler
import ru.zolotarev.relocate.service.CountryService
import ru.zolotarev.relocate.service.StartService
import ru.zolotarev.relocate.service.TestService

@Component
class Bot(
    botConfig: BotConfig,
    private val startService: StartService,
    private val countryService: CountryService,
    private val testService: TestService,
    private val callbackHandler: CallbackHandler
) : TelegramLongPollingBot() {
    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    private val token: String = botConfig.token
    private val username: String = botConfig.username

    //TODO Убрать все действия со страной в 1 класс
    //TODO Придумать как сделать 1 енам с городами(выбор в клавиатуру в зависимости от страны)
    //TODO Сделать обработку ошибок
    //TODO Сделать логирование
    //TODO Рефактор названий классов + удалить неиспользуемое

    override fun onUpdateReceived(update: Update) {
        logger.info("Update is: $update")

        if (update.hasCallbackQuery()) {
            logger.info("Input callback query: ${update.callbackQuery}")
            sendMessage(callbackHandler.handle(update))
        }

        if (update.hasMessage() && update.message.hasText()) {
            val message = update.message

            if (message.hasText() && message.hasEntities()) {
                when (message.text) {
                    Command.START.value -> sendMessage(
                        startService.startCommandProcessing(update).getOrElse { throw IllegalStateException() }
                    )
                }
            } else {
                when (message.text) {
                    KeyboardButton.COUNTRY.description -> sendMessage(
                        countryService.countryCommandProcessing(update).getOrElse { throw IllegalStateException() }
                    )

                    KeyboardButton.TEST.description -> sendMessage(
                        testService.testCommandProcessing(update).getOrElse { throw IllegalStateException() }
                    )
                }
            }
        }
    }

    private fun sendMessage(sendMessage: SendMessage) {
        try {
            logger.info(sendMessage.toString())
            execute(sendMessage)
        } catch (e: Exception) {
            logger.error("Can`t send message to user! Cause: $e")
        }
    }

    override fun getBotToken(): String {
        return token
    }

    override fun getBotUsername(): String {
        return username
    }
}