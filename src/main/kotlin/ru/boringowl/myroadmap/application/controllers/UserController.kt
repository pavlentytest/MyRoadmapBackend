package ru.boringowl.myroadmap.application.controllers

import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.boringowl.myroadmap.application.dto.LoginData
import ru.boringowl.myroadmap.application.dto.RegisterData
import ru.boringowl.myroadmap.application.dto.UserTokenData
import ru.boringowl.myroadmap.application.services.PostService
import ru.boringowl.myroadmap.application.services.UserService
import ru.boringowl.myroadmap.infrastructure.security.JwtUtils
import ru.boringowl.myroadmap.infrastructure.security.UserDetailsService

@RestController
@RequestMapping("/auth")
class UserController(
    val service: PostService,
    private val authenticationManager: AuthenticationManager,
    private val jwtUtils: JwtUtils,
    private val userDetailsService: UserDetailsService,
    private val userService: UserService
) {

    @PostMapping("login")
    fun auth(
        @RequestBody
        credentials: LoginData,
    ): ResponseEntity<UserTokenData> {
        val username = credentials.username
        val password = credentials.password
        val authentication = UsernamePasswordAuthenticationToken(username, password)
        authenticationManager.authenticate(authentication)

        val userDetails = userDetailsService.loadUserByUsername(username)
        val token = jwtUtils.generateToken(userDetails)
        val tokenData = UserTokenData(token)
        return ResponseEntity.ok(tokenData)
    }

    @PostMapping("register")
    fun register(
        @RequestBody
        userData: RegisterData,
    ): ResponseEntity<UserTokenData> {

        val user = userService.add(userData)

        val credentials = LoginData(user.username, user.password)
        return auth(credentials)
    }

}
