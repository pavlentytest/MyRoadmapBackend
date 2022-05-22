package ru.boringowl.myroadmap.application.persistence

import org.springframework.data.repository.CrudRepository
import ru.boringowl.myroadmap.infrastructure.jpa.JpaSkill
import ru.boringowl.myroadmap.infrastructure.jpa.JpaUser
import java.util.*
interface UserRepo : CrudRepository<JpaUser, UUID> {
    fun findByUsername(username: String) : JpaUser?
    fun existsByUsername(username: String) : Boolean
    fun existsByEmail(email: String) : Boolean
}

