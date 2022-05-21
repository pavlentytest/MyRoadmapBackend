package ru.boringowl.myroadmap.application.controllers

import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.*
import ru.boringowl.myroadmap.application.dto.LoginData
import ru.boringowl.myroadmap.application.dto.RegisterData
import ru.boringowl.myroadmap.application.dto.UserTokenData
import ru.boringowl.myroadmap.application.services.UserService
import ru.boringowl.myroadmap.domain.User
import ru.boringowl.myroadmap.infrastructure.security.JwtUtils
import ru.boringowl.myroadmap.infrastructure.security.UserDetailsService

@RestController
@RequestMapping("/auth")
class UserController(
    private val authenticationManager: AuthenticationManager,
    private val jwtUtils: JwtUtils,
    private val userDetailsService: UserDetailsService,
    private val userService: UserService
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
    @GetMapping
    fun me(
        @RequestHeader("Authorization") token: String,
    ): User {
        val username = jwtUtils.extractUsername(token.removePrefix("Bearer "))
        return userService.get(username)
    }

}
