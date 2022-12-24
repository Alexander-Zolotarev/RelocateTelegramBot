package ru.zolotarev.relocate.service.impl

import io.vavr.control.Either
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.Update
import ru.zolotarev.relocate.model.error.EitherError
import ru.zolotarev.relocate.model.response.TestCommandResponse
import ru.zolotarev.relocate.service.TestService

@Service
class TestServiceImpl : TestService {
    override fun testCommandProcessing(update: Update): Either<EitherError, TestCommandResponse> {
        TODO("Not yet implemented")
    }

}

