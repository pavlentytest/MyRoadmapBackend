package ru.boringowl.myroadmap.infrastructure.security

import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import ru.boringowl.myroadmap.application.persistence.UserRepo

@Service
class UserDetailsService(private val repository: UserRepo) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        val user = repository.findByUsername(username)
                ?: throw UsernameNotFoundException("Пользователь $username не найден")
        return User(user.username, user.password, setOf(SimpleGrantedAuthority(user.role.toString())))
    }
}
