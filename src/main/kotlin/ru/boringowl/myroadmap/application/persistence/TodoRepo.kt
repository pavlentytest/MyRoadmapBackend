package ru.boringowl.myroadmap.application.persistence

import org.springframework.data.jpa.repository.JpaRepository
import ru.boringowl.myroadmap.infrastructure.jpa.JpaSkill
import ru.boringowl.myroadmap.infrastructure.jpa.JpaTodo
import ru.boringowl.myroadmap.infrastructure.jpa.JpaUser
import java.util.*

interface TodoRepo : JpaRepository<JpaTodo, UUID> {
    fun findAllByUser_UserId(userId: UUID): List<JpaTodo>
}

