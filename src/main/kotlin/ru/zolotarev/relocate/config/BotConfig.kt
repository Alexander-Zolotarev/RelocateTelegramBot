package ru.zolotarev.relocate.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component
import javax.validation.constraints.NotBlank

@Component
@ConfigurationProperties("ru.zolotarev.relocate.telegram.bot")
class BotConfig {
    @NotBlank
    lateinit var token: String
    lateinit var username: String
}