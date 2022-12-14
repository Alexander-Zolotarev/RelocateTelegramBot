package ru.zolotarev.relocate.service.impl

import io.vavr.control.Either
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton
import ru.zolotarev.relocate.model.Country
import ru.zolotarev.relocate.model.error.EitherError
import ru.zolotarev.relocate.service.CountryService
import ru.zolotarev.relocate.utils.BotUtils.chatId

@Service
class CountryServiceImpl : CountryService{
    override fun countryCommandProcessing(update: Update): Either<EitherError, SendMessage> {
        val buttons: MutableList<MutableList<InlineKeyboardButton>> = ArrayList()
        Country.values().map {
            val button: MutableList<InlineKeyboardButton> = mutableListOf(InlineKeyboardButton.builder()
                .text(it.description)
                .callbackData(it.name)
                .build())
            buttons.add(button)
        }

        val sendMessage = SendMessage()
        sendMessage.text = "Выберите страну"
        sendMessage.chatId = chatId(update)
        sendMessage.replyMarkup = InlineKeyboardMarkup.builder().keyboard(buttons).build()
        return Either.right(sendMessage)
    }
}