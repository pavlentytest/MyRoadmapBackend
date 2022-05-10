package ru.boringowl.myroadmap.infrastructure.jpa

import ru.boringowl.myroadmap.domain.Route
import ru.boringowl.myroadmap.domain.Skill
import java.util.*
import javax.persistence.*

@Entity
@Table(name="skills_table")
class JpaSkill() {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "skill_id")
    var skillId: UUID? = null

    @Column(name = "skill_name")
    var skillName: String = ""

    @ManyToOne
    var route: JpaRoute? = null



    constructor(skill: Skill) : this() {
        skillId = skill.skillId
        skillName = skill.skillName
        route = skill.route?.let { JpaRoute(it) }
    }

    fun toSkill() = Skill().also {
        it.skillId = skillId
        it.skillName = skillName
        it.route = route?.toRoute()
    }
}
