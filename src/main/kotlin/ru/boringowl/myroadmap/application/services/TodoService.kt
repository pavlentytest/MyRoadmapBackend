package ru.boringowl.myroadmap.application.services

import org.springframework.stereotype.Service
import ru.boringowl.myroadmap.application.persistence.TodoRepo
import ru.boringowl.myroadmap.domain.Todo
import ru.boringowl.myroadmap.infrastructure.jpa.JpaTodo
import java.util.*

@Service
class TodoService(val todoRepo: TodoRepo) : BaseService<Todo, JpaTodo, UUID>(todoRepo) {
    override fun toJpa(dto: Todo): JpaTodo? = JpaTodo(dto)
    override fun toDto(jpa: JpaTodo?): Todo? = jpa?.toTodo()
    override fun getId(dto: Todo): UUID? = dto.todoId
}
