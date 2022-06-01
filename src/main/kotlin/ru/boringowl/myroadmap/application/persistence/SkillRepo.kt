package ru.boringowl.myroadmap.application.persistence

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import ru.boringowl.myroadmap.infrastructure.jpa.JpaSkill
import java.util.*

interface SkillRepo : JpaRepository<JpaSkill, UUID> {
    fun findByRoute_RouteIdAndSkillName(routeId: Int, skillName: String): JpaSkill?

    fun findAllByRoute_RouteId(routeId: Int, pageRequest: Pageable): Page<JpaSkill>
}

