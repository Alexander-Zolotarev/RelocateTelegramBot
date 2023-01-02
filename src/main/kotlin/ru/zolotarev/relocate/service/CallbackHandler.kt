package ru.zolotarev.relocate.service

import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update

interface CallbackHandler {

    fun handle(update: Update): SendMessage
}