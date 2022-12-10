package ru.zolotarev.relocate.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration
import javax.validation.constraints.NotBlank

@Configuration
@ConfigurationProperties("ru.zolotarev.relocate.telegram.bot")
open class BotConfig {
    @NotBlank
    lateinit var token: String

    @NotBlank
    lateinit var username: String
}