package ru.boringowl.myroadmap.application.services

import org.springframework.stereotype.Service
import ru.boringowl.myroadmap.application.persistence.SkillRepo
import ru.boringowl.myroadmap.application.persistence.SkillTodoRepo
import ru.boringowl.myroadmap.application.persistence.TodoRepo
import ru.boringowl.myroadmap.domain.SkillTodo
import ru.boringowl.myroadmap.infrastructure.jpa.JpaSkillTodo
import java.util.*

@Service
class SkillTodoService(
    val skillTodoRepo: SkillTodoRepo,
    val skillRepo: SkillRepo,
    val todoRepo: TodoRepo,
) : BaseService<SkillTodo, JpaSkillTodo, UUID>(skillTodoRepo) {
    override fun toJpa(dto: SkillTodo): JpaSkillTodo? = JpaSkillTodo(dto)
    override fun toDto(jpa: JpaSkillTodo?): SkillTodo? = jpa?.toSkillTodo()
    override fun getId(dto: SkillTodo): UUID? = dto.skillTodoId
    fun getByTodo(todoId: UUID) : List<SkillTodo> {
        return skillTodoRepo.findAllByTodo_TodoId(todoId).map {it.toSkillTodo()}
    }

    fun update(id: UUID, progress: Int): SkillTodo? {
        val st = skillTodoRepo.findById(id).orElse(null)
        st.progress = progress
        return toDto(skillTodoRepo.save(st))
    }

}
