package ru.zolotarev.relocate.model

enum class Command(
    val value:String
) {
    START("/start"),
    COUNTRY("/country"),
    TEST("/test"),
}