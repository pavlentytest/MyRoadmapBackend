package ru.boringowl.myroadmap.application.persistence

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import ru.boringowl.myroadmap.infrastructure.jpa.JpaSkillTodo
import java.util.*

interface SkillTodoRepo : JpaRepository<JpaSkillTodo, UUID> {
    fun findAllByTodo_TodoId(todoId: UUID, pageRequest: Pageable): Page<JpaSkillTodo>
}

