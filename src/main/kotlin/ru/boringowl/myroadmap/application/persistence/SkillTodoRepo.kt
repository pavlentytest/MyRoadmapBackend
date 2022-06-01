package ru.boringowl.myroadmap.application.persistence

import org.springframework.data.jpa.repository.JpaRepository
import ru.boringowl.myroadmap.infrastructure.jpa.JpaSkillTodo
import java.util.*

interface SkillTodoRepo : JpaRepository<JpaSkillTodo, UUID> {
    fun findAllByTodo_TodoId(todoId: UUID): List<JpaSkillTodo>
}

