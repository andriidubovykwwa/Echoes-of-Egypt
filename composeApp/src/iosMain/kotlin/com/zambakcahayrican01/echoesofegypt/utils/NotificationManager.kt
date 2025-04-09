package com.zambakcahayrican01.echoesofegypt.utils

import OneSignal.OneSignal
import kotlinx.cinterop.ExperimentalForeignApi

class NotificationManager {

    @OptIn(ExperimentalForeignApi::class)
    fun init(id: String, tag: String, onFinish: () -> Unit) {
        OneSignal.promptForPushNotificationsWithUserResponse {
            OneSignal.setExternalUserId(id)
            OneSignal.sendTag("sub_app", tag)
            onFinish()
        }
    }

    companion object {
        const val KEY = "bd239e63-b435-4e1f-9233-668540aa6619"
    }
}