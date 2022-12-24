package ru.zolotarev.relocate.service.impl

import io.vavr.control.Either
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.Update
import ru.zolotarev.relocate.model.error.EitherError
import ru.zolotarev.relocate.model.response.CountryCommandResponse
import ru.zolotarev.relocate.service.CountryService

@Service
class CountryServiceImpl : CountryService{
    override fun countryCommandProcessing(update: Update): Either<EitherError, CountryCommandResponse> {
        TODO("Not yet implemented")
    }
}