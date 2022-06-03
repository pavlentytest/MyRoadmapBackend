package ru.boringowl.myroadmap.infrastructure.mail

open class Request

data class EmailRequest(var receiverEmail: String, var subject: String?, var message: String?) : Request()

interface NotificationService {
    fun send(request: Request)
}

interface EmailNotificationService : NotificationService {
    fun send(emailRequest: EmailRequest)
}
