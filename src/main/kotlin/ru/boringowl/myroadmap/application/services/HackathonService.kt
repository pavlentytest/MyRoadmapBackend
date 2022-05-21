package ru.boringowl.myroadmap.application.services

import org.springframework.stereotype.Service
import ru.boringowl.myroadmap.application.persistence.HackathonRepo
import ru.boringowl.myroadmap.domain.Hackathon
import ru.boringowl.myroadmap.infrastructure.jpa.JpaHackathon
import ru.boringowl.myroadmap.infrastructure.utils.paginate
import java.util.*

@Service
class HackathonService(val hackathonRepo: HackathonRepo) : BaseService<Hackathon, JpaHackathon, UUID>(hackathonRepo) {
    override fun toJpa(dto: Hackathon): JpaHackathon? = JpaHackathon(dto)
    override fun toDto(jpa: JpaHackathon?): Hackathon? = jpa?.toHackathon()
    override fun getId(dto: Hackathon): UUID? = dto.hackId

    fun existsBySourceAndDate(source: String, date: String?): Boolean = hackathonRepo.existsBySourceAndDate(source, date)
    fun get(page: Int, perPage: Int): List<Hackathon> {
        val hackathons = hackathonRepo.findAll().toList()
        return hackathons.paginate(page, perPage).map { it.toHackathon() }
    }
}
