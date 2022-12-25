package ru.zolotarev.relocate.service

import io.vavr.control.Either
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update
import ru.zolotarev.relocate.model.error.EitherError

interface StartService {
    fun startCommandProcessing(update: Update) : Either<EitherError, SendMessage>
}