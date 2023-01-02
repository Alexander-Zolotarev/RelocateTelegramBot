package ru.zolotarev.relocate.service.impl

import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update
import ru.zolotarev.relocate.model.Country
import ru.zolotarev.relocate.service.CallbackHandler
import ru.zolotarev.relocate.service.CountryCallbackHandler
import ru.zolotarev.relocate.utils.BotUtils.sendMessageWithWaitText

@Service
class CallbackHandlerImpl (
    private val countryCallbackHandler: CountryCallbackHandler
        ) : CallbackHandler {
    override fun handle(update: Update): SendMessage {
        val callbackQuery = update.callbackQuery

        return when (callbackQuery.data) {
            Country.KAZAKHSTAN.name -> countryCallbackHandler.handle(callbackQuery)
            Country.TURKEY.name -> update.sendMessageWithWaitText()
            Country.GEORGIA.name -> update.sendMessageWithWaitText()
            else -> update.sendMessageWithWaitText()
        }
    }
}