package ru.zolotarev.relocate.service.impl

import io.vavr.control.Either
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow
import ru.zolotarev.relocate.model.error.EitherError
import ru.zolotarev.relocate.service.StartService
import ru.zolotarev.relocate.utils.BotUtils

@Service
class StartServiceImpl : StartService {
    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    override fun startCommandProcessing(update: Update) : Either<EitherError, SendMessage> {
        val sendMessage = SendMessage()
        val keyboardMarkup = ReplyKeyboardMarkup()
        keyboardMarkup.resizeKeyboard = true
        keyboardMarkup.selective = true
        val keyboard: MutableList<KeyboardRow> = ArrayList()
        val row = KeyboardRow()
        val selectCountry = KeyboardButton("Выбрать страну")
        val selectTest = KeyboardButton("Пройти тест")
        row.add(selectCountry)
        row.add(selectTest)
        keyboard.add(row)
        keyboardMarkup.keyboard = keyboard

        sendMessage.replyMarkup = keyboardMarkup
        sendMessage.chatId = BotUtils.chatId(update)
        sendMessage.text = "Выберите действие"
        return Either.right(sendMessage)
    }
}