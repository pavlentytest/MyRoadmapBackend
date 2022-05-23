package ru.boringowl.myroadmap.application.services

import org.springframework.stereotype.Service
import ru.boringowl.myroadmap.application.persistence.TodoRepo
import ru.boringowl.myroadmap.domain.SkillTodo
import ru.boringowl.myroadmap.domain.Todo
import ru.boringowl.myroadmap.infrastructure.jpa.JpaTodo
import java.util.*

@Service
class TodoService(val todoRepo: TodoRepo,  val skillService: SkillService, val userService: UserService) : BaseService<Todo, JpaTodo, UUID>(todoRepo) {
    override fun toJpa(dto: Todo): JpaTodo? = JpaTodo(dto)
    override fun toDto(jpa: JpaTodo?): Todo? = jpa?.toTodo()
    override fun getId(dto: Todo): UUID? = dto.todoId
    fun addByRoute(routeId: Int, name: String, username: String): Todo? {
        val skills = skillService.getByRouteId(routeId).first
        val user = userService.get(username)
        val todo = Todo().apply {
            header = name
            this.user = user
            this.skills = skills.map { SkillTodo().apply { this.skill = it }}
        }
        val saved = add(todo)
        saved?.skills?.forEach {
            it.skill?.route = null
        }
        return saved
    }
    fun get(username: String): List<Todo> {
        val userId = userService.get(username).userId
        require(userId != null) { "Пользователь не существует" }
        return todoRepo.findAllByUser_UserId(userId).map { it.toTodo() }
    }
}
