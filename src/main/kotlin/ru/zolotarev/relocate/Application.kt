package ru.zolotarev.relocate

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.telegram.telegrambots.meta.TelegramBotsApi
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession
import ru.zolotarev.relocate.telegram.Bot

@SpringBootApplication
open class Application(
    private val bot: Bot
) : CommandLineRunner {

    override fun run(vararg args: String?) {
        val telegramBotsApi = TelegramBotsApi(DefaultBotSession::class.java)
        telegramBotsApi.registerBot(bot)
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}