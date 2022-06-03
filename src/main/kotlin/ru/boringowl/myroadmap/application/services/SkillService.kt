package ru.boringowl.myroadmap.application.services

import org.springframework.stereotype.Service
import ru.boringowl.myroadmap.application.persistence.SkillRepo
import ru.boringowl.myroadmap.domain.Skill
import ru.boringowl.myroadmap.infrastructure.jpa.JpaSkill
import java.util.*

@Service
class SkillService(val skillRepo: SkillRepo) : BaseService<Skill, JpaSkill, UUID>(skillRepo) {
    fun saveByNameAndRoute(skill: Skill) {
        val foundSkill = skill.route?.routeId?.let {
            skillRepo.findByRoute_RouteIdAndSkillName(it, skill.skillName)
        }
        foundSkill?.let {
            it.necessity = skill.necessity
            skillRepo.save(it)
            return
        }
        skillRepo.save(toJpa(skill)!!)
    }
    fun getByRouteId(routeId: Int): List<Skill> =
        skillRepo.findAllByRoute_RouteId(routeId).map { toDto(it)!! }


    override fun toJpa(dto: Skill): JpaSkill? = JpaSkill(dto)
    override fun toDto(jpa: JpaSkill?): Skill? = jpa?.toSkill()
    override fun getId(dto: Skill): UUID? = dto.skillId
}
