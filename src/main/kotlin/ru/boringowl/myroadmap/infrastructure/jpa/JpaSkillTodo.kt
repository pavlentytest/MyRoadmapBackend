package ru.boringowl.myroadmap.infrastructure.jpa

import ru.boringowl.myroadmap.domain.SkillTodo
import java.util.*
import javax.persistence.*

@Entity
@Table(name="skill_todo", uniqueConstraints = [UniqueConstraint(columnNames = ["skill_id", "todo_id"])])
class JpaSkillTodo() {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="skill_todo_id")
    var skillTodoId: UUID? = null

    @ManyToOne
    @JoinColumn(name="skill_id")
    var skill: JpaSkill? = null

    @ManyToOne
    @JoinColumn(name="todo_id")
    var todo: JpaTodo? = null

    @Column(name="progress")
    var progress: Int = 0
    @Column(columnDefinition="TEXT")
    var notes: String = ""



    constructor(skillTodo: SkillTodo) : this() {
        skillTodoId = skillTodo.skillTodoId
        progress = skillTodo.progress
        notes = skillTodo.notes
        skill = skillTodo.skill?.let { JpaSkill(it) }
        todo = skillTodo.todo?.let { JpaTodo(it, false) }
    }

    fun toSkillTodo(short: Boolean = false) = SkillTodo().also {
        it.skillTodoId = skillTodoId
        it.progress = progress
        it.notes = notes
        it.skill = skill?.toSkill(short)
    }
}

