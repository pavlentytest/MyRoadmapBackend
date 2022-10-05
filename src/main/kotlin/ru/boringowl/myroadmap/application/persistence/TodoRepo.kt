package ru.boringowl.myroadmap.application.persistence

import org.springframework.data.jpa.repository.JpaRepository
import ru.boringowl.myroadmap.infrastructure.jpa.JpaTodo
import java.util.*

interface TodoRepo : JpaRepository<JpaTodo, UUID> {
    fun findAllByUser_Id(userId: UUID): List<JpaTodo>
}

