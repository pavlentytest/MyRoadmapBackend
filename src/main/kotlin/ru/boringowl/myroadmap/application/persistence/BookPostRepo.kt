package ru.boringowl.myroadmap.application.persistence

import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.jpa.repository.JpaRepository
import ru.boringowl.myroadmap.infrastructure.jpa.JpaBookPost
import java.util.*

interface BookPostRepo : JpaRepository<JpaBookPost, UUID> {
    fun findAllByRoute_RouteId(routeId: Int, pageRequest: PageRequest): Page<JpaBookPost>
    fun existsByDescription(desc: String): Boolean
}

