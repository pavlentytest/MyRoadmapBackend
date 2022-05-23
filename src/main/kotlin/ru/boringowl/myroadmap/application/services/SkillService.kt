package ru.boringowl.myroadmap.application.services

import org.springframework.stereotype.Service
import ru.boringowl.myroadmap.application.persistence.SkillRepo
import ru.boringowl.myroadmap.domain.Skill
import ru.boringowl.myroadmap.infrastructure.jpa.JpaSkill
import ru.boringowl.myroadmap.infrastructure.utils.paginate
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
    fun getByRouteId(id: Int, page: Int = 1, perPage: Int = 20, full: Boolean = false): Triple<List<Skill>, Int?, Int?> {
        val res = skillRepo.findAllByRoute_RouteId(id).map { it.toSkill(true)}
        return if (full) Triple(res, null, null) else res.paginate(page, perPage)
    }


    override fun toJpa(dto: Skill): JpaSkill? = JpaSkill(dto)
    override fun toDto(jpa: JpaSkill?): Skill? = jpa?.toSkill()
    override fun getId(dto: Skill): UUID? = dto.skillId
}
