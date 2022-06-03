package ru.boringowl.myroadmap.application.persistence

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import ru.boringowl.myroadmap.infrastructure.jpa.JpaBookPost
import java.util.*

interface BookPostRepo : JpaRepository<JpaBookPost, UUID> {
    fun findAllByRoute_RouteId(routeId: Int, pageRequest: Pageable): Page<JpaBookPost>
    fun existsByDescription(desc: String): Boolean

    fun findAllByRoute_RouteIdAndDescriptionContainsIgnoreCase(routeId: Int, text: String, pageRequest: Pageable): Page<JpaBookPost>
}

