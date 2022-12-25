package ru.zolotarev.relocate.service.impl

import io.vavr.control.Either
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update
import ru.zolotarev.relocate.model.error.EitherError
import ru.zolotarev.relocate.service.CountryService
import ru.zolotarev.relocate.utils.BotUtils.sendMessageWithWaitText

@Service
class CountryServiceImpl : CountryService{
    override fun countryCommandProcessing(update: Update): Either<EitherError, SendMessage> {
        return Either.right(update.sendMessageWithWaitText())
    }
}