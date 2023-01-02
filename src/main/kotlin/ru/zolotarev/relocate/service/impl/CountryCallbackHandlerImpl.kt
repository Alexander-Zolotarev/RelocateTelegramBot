package ru.zolotarev.relocate.service.impl

import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.CallbackQuery
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton
import ru.zolotarev.relocate.model.CityOfKazakhstan
import ru.zolotarev.relocate.service.CountryCallbackHandler
import ru.zolotarev.relocate.utils.BotUtils.chatId

@Service
class CountryCallbackHandlerImpl : CountryCallbackHandler {
    override fun textAboutCountry(callbackQuery: CallbackQuery): String {
        return "Some information about ${callbackQuery.data}"
    }

    override fun handle(callbackQuery: CallbackQuery): SendMessage {
        val buttons: MutableList<MutableList<InlineKeyboardButton>> = ArrayList()
        CityOfKazakhstan.values().map {
            val button: MutableList<InlineKeyboardButton> = mutableListOf(
                InlineKeyboardButton.builder()
                .text(it.description)
                .callbackData(it.name)
                .build())
            buttons.add(button)
        }

        val sendMessage = SendMessage()
        sendMessage.text = textAboutCountry(callbackQuery)
        sendMessage.chatId = callbackQuery.chatId()
        sendMessage.replyMarkup = InlineKeyboardMarkup.builder().keyboard(buttons).build()
        return sendMessage
    }
}