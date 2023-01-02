package ru.zolotarev.relocate.service

import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.CallbackQuery
import org.telegram.telegrambots.meta.api.objects.Update

interface CountryCallbackHandler {
    fun textAboutCountry(callbackQuery: CallbackQuery) : String

    fun handle(callbackQuery: CallbackQuery) : SendMessage
}