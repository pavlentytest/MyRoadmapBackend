package ru.boringowl.myroadmap.application.services

import org.springframework.stereotype.Service
import ru.boringowl.myroadmap.application.persistence.SkillRepo
import ru.boringowl.myroadmap.domain.Route
import ru.boringowl.myroadmap.domain.Skill
import ru.boringowl.myroadmap.infrastructure.jpa.JpaSkill
import java.util.*

@Service
class SkillService(val skillRepo: SkillRepo) : BaseService<Skill, JpaSkill, UUID>(skillRepo) {
    override fun toJpa(dto: Skill): JpaSkill? = JpaSkill(dto)
    override fun toDto(jpa: JpaSkill?): Skill? = jpa?.toSkill()
    override fun getId(dto: Skill): UUID? = dto.skillId
}
