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
) : BaseService<SkillTodo, JpaSkillTodo, UUID>(skillTodoRepo) {
    override fun toJpa(dto: SkillTodo): JpaSkillTodo? = JpaSkillTodo(dto)
    override fun toDto(jpa: JpaSkillTodo?): SkillTodo? = jpa?.toSkillTodo()
    override fun getId(dto: SkillTodo): UUID? = dto.skillTodoId
    fun get(skillId: UUID, todoId: UUID) : SkillTodo? {
        return skillTodoRepo.findBySkill_SkillIdAndTodo_TodoId(skillId, todoId)?.toSkillTodo()
    }
    fun getByTodo(todoId: UUID) : List<SkillTodo> {
        return skillTodoRepo.findAllByTodo_TodoId(todoId).map {it.toSkillTodo()}
    }
    fun delete(skillId: UUID, todoId: UUID) {
        skillTodoRepo.deleteBySkill_SkillIdAndTodo_TodoId(skillId, todoId)
    }

    fun update(id: UUID, progress: Int): SkillTodo? {
        val st = skillTodoRepo.findById(id).orElse(null)
        st.progress = progress
        return toDto(skillTodoRepo.save(st))
    }

}
