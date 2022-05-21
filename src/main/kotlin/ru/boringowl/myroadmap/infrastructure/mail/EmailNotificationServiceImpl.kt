package ru.boringowl.myroadmap.infrastructure.mail

import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Component

@Component
class EmailNotificationServiceImpl(private val mailSender : JavaMailSender) : EmailNotificationService {
    override fun send(emailRequest: EmailRequest) {
        val simpleMailMessage = SimpleMailMessage()
        simpleMailMessage.setTo(emailRequest.receiverEmail)
        emailRequest.subject?.let { simpleMailMessage.setSubject(it) }
        emailRequest.message?.let { simpleMailMessage.setText(it) }

        mailSender.send(simpleMailMessage)
    }

    override fun send(request: Request) = if (request is EmailRequest) {
        send(request)
    } else {
        throw UnsupportedOperationException()
    }
}
