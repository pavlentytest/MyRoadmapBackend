package ru.boringowl.myroadmap.application.controllers

import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.*
import ru.boringowl.myroadmap.application.dto.LoginData
import ru.boringowl.myroadmap.application.dto.RegisterData
import ru.boringowl.myroadmap.application.dto.ResetPasswordData
import ru.boringowl.myroadmap.application.dto.UserTokenData
import ru.boringowl.myroadmap.application.services.UserService
import ru.boringowl.myroadmap.domain.User
import ru.boringowl.myroadmap.infrastructure.mail.EmailNotificationService
import ru.boringowl.myroadmap.infrastructure.mail.EmailRequest
import ru.boringowl.myroadmap.infrastructure.security.JwtUtils
import ru.boringowl.myroadmap.infrastructure.security.UserDetailsService
import ru.boringowl.myroadmap.infrastructure.utils.getRandPassword

@RestController
@RequestMapping("/auth")
class UserController(
    private val authenticationManager: AuthenticationManager,
    private val jwtUtils: JwtUtils,
    private val userDetailsService: UserDetailsService,
    private val userService: UserService,
    private val mailService: EmailNotificationService
) {

    @PostMapping("login")
    fun auth(
        @RequestBody
        credentials: LoginData,
    ): UserTokenData {
        val username = credentials.username
        val password = credentials.password
        val authentication = UsernamePasswordAuthenticationToken(username, password)
        authenticationManager.authenticate(authentication)

        val userDetails = userDetailsService.loadUserByUsername(username)
        val token = jwtUtils.generateToken(userDetails)
        return UserTokenData(token)
    }

    @PostMapping("register")
    fun register(
        @RequestBody
        userData: RegisterData,
    ): UserTokenData {
        userService.add(userData)
        val credentials = LoginData(userData.username, userData.password)
        return auth(credentials)
    }

    @PostMapping("resetPassword")
    fun resetPassword(
        @RequestBody userData: ResetPasswordData,
    ): ResponseEntity<String> {
        val user = userService.get(userData.username)
        require(user.email == userData.email) {"Почта и имя пользователя не совпадают"}
        val newPass = getRandPassword(14)
        userService.setUserPassword(user.userId!!, newPass)
        mailService.send(
            EmailRequest(
                user.email,
                "Восстановление пароля",
                "Установлен пароль: $newPass\nВам необходимо зайти и поменять его в профиле."
            )
        )
        return ResponseEntity.ok("Запрос отправлен на почту")
    }

    @GetMapping
    fun me(
        @RequestHeader("Authorization") token: String,
    ): User {
        val username = jwtUtils.extractUsername(token.removePrefix("Bearer "))
        return userService.get(username)
    }

}
