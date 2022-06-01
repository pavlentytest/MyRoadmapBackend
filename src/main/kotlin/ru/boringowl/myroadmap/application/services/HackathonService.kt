package ru.boringowl.myroadmap.application.services

import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import ru.boringowl.myroadmap.application.persistence.HackathonRepo
import ru.boringowl.myroadmap.domain.Hackathon
import ru.boringowl.myroadmap.infrastructure.jpa.JpaHackathon
import java.util.*

@Service
class HackathonService(val hackathonRepo: HackathonRepo) : BaseService<Hackathon, JpaHackathon, UUID>(hackathonRepo) {
    override fun toJpa(dto: Hackathon): JpaHackathon? = JpaHackathon(dto)
    override fun toDto(jpa: JpaHackathon?): Hackathon? = jpa?.toHackathon()
    override fun getId(dto: Hackathon): UUID? = dto.hackId

    fun getByQuery(pageable: Pageable, query: String): Page<Hackathon> =
        if (query.isNotEmpty())
            get(pageable)
        else
            PageImpl(get().filter { it.containsText(query) })

    fun existsBySourceAndDate(source: String, date: String?): Boolean = hackathonRepo.existsBySourceAndDate(source, date)
}
