package ru.boringowl.myroadmap.application.persistence

import org.springframework.data.repository.CrudRepository
import ru.boringowl.myroadmap.infrastructure.jpa.JpaHackathon
import java.util.*

interface HackathonRepo : CrudRepository<JpaHackathon, UUID> {
    fun existsBySourceAndDate(source: String, date: String?): Boolean
}
