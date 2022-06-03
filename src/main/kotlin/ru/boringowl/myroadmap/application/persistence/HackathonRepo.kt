package ru.boringowl.myroadmap.application.persistence

import org.springframework.data.jpa.repository.JpaRepository
import ru.boringowl.myroadmap.infrastructure.jpa.JpaHackathon
import java.util.*

interface HackathonRepo : JpaRepository<JpaHackathon, UUID> {
    fun existsBySourceAndDate(source: String, date: String?): Boolean
}
