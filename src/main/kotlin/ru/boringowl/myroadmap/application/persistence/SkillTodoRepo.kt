package ru.boringowl.myroadmap.application.persistence

import org.springframework.data.repository.CrudRepository
import ru.boringowl.myroadmap.infrastructure.jpa.JpaSkill
import ru.boringowl.myroadmap.infrastructure.jpa.JpaSkillTodo
import ru.boringowl.myroadmap.infrastructure.jpa.JpaTodo
import java.util.*

interface SkillTodoRepo : CrudRepository<JpaSkillTodo, UUID> {
    fun findBySkill_SkillIdAndTodo_TodoId(skillId: UUID, todoId: UUID): JpaSkillTodo?
    fun deleteBySkill_SkillIdAndTodo_TodoId(skillId: UUID, todoId: UUID)
    fun findAllByTodo_TodoId(todoId: UUID): List<JpaSkillTodo>
}

