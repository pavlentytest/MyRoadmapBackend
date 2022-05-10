package ru.boringowl.myroadmap.infrastructure.jpa

import ru.boringowl.myroadmap.domain.Skill
import ru.boringowl.myroadmap.domain.SkillTodo
import java.util.*
import javax.persistence.*

@Entity
@Table(name="skill_todo")
@IdClass(JpaSkillTodoId::class)
class JpaSkillTodo() {
    @Id
    @ManyToOne
    var skill: JpaSkill? = null
    @Id
    @ManyToOne
    var todo: JpaTodo? = null

    @Column(name="progress")
    var progress: Int = 0
    @Column(columnDefinition="TEXT")
    var notes: String = ""



    constructor(skillTodo: SkillTodo) : this() {
        progress = skillTodo.progress
        notes = skillTodo.notes
        skill = skillTodo.skill?.let { JpaSkill(it) }
        todo = skillTodo.todo?.let { JpaTodo(it) }
    }

    fun toSkillTodo() = SkillTodo().also {
        it.progress = progress
        it.notes = notes
        it.skill = skill?.toSkill()
        it.todo = todo?.toTodo()
    }
}


class JpaSkillTodoId : java.io.Serializable {
    @Id
    @ManyToOne
    var skill: JpaSkill? = null
    @Id
    @ManyToOne
    var todo: JpaTodo? = null
}
