package ru.boringowl.myroadmap.application.persistence

import org.springframework.data.repository.CrudRepository
import ru.boringowl.myroadmap.infrastructure.jpa.JpaBookPost
import java.util.*

interface BookPostRepo : CrudRepository<JpaBookPost, UUID> {
    fun findAllByRoute_RouteId(routeId: Int): List<JpaBookPost>
    fun existsByDescription(desc: String): Boolean
}

