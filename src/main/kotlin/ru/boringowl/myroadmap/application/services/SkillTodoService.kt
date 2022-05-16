package ru.boringowl.myroadmap.application.services

import org.springframework.stereotype.Service
import ru.boringowl.myroadmap.application.persistence.*
import ru.boringowl.myroadmap.domain.SkillTodo
import ru.boringowl.myroadmap.infrastructure.jpa.*
import java.util.*

@Service
class SkillTodoService(
    val skillTodoRepo: SkillTodoRepo,
    val skillRepo: SkillRepo,
    val todoRepo: TodoRepo,
) : BaseService<SkillTodo, JpaSkillTodo, JpaSkillTodoId>(skillTodoRepo) {
    override fun toJpa(dto: SkillTodo): JpaSkillTodo? = JpaSkillTodo(dto)
    override fun toDto(jpa: JpaSkillTodo?): SkillTodo? = jpa?.toSkillTodo()
    override fun getId(dto: SkillTodo): JpaSkillTodoId? =
        JpaSkillTodoId().apply {
            skill = dto.skill?.let { JpaSkill(it) }
            todo = dto.todo?.let { JpaTodo(it) }
        }
    fun get(skillId: UUID, todoId: UUID) : SkillTodo? {
        val s = skillRepo.findById(skillId)
        val t = todoRepo.findById(todoId)
        require(s.isPresent && t.isPresent) { "Запись не найдена" }
        val st = JpaSkillTodoId().apply {
            skill = s.get()
            todo = t.get()
        }
        return skillTodoRepo.findById(st).orElse(null)?.toSkillTodo()
    }
    fun delete(skillId: UUID, todoId: UUID) {
        val s = skillRepo.findById(skillId)
        val t = todoRepo.findById(todoId)
        require(s.isPresent && t.isPresent) { "Данные не валидны" }
        val st = JpaSkillTodoId().apply {
            skill = s.get()
            todo = t.get()
        }
        skillTodoRepo.deleteById(st)
    }
}
