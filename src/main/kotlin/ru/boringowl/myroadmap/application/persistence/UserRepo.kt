package ru.boringowl.myroadmap.application.persistence

import org.springframework.data.jpa.repository.JpaRepository
import ru.boringowl.myroadmap.infrastructure.jpa.JpaUser
import java.util.*

interface UserRepo : JpaRepository<JpaUser, UUID> {
    fun findByUsername(username: String) : JpaUser?
    fun existsByUsername(username: String) : Boolean
    fun existsByEmail(email: String) : Boolean
}

