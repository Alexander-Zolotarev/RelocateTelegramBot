package ru.zolotarev.relocate.service

import io.vavr.control.Either
import org.telegram.telegrambots.meta.api.objects.Update
import ru.zolotarev.relocate.model.error.EitherError
import ru.zolotarev.relocate.model.response.TestCommandResponse

interface TestService {
    fun testCommandProcessing(update: Update) : Either<EitherError, TestCommandResponse>
}