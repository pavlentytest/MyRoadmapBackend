package ru.boringowl.myroadmap.infrastructure.jpa

import ru.boringowl.myroadmap.domain.SkillTodo
import ru.boringowl.myroadmap.domain.Todo
import java.util.*
import javax.persistence.*

@Entity
@Table(name="skill_todo")
class JpaSkillTodo() {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="skill_todo_id")
    var skillTodoId: UUID? = null

    @Column(name="skill_name")
    var skillName: String = ""

    @Column(name="manual_name")
    var manualName: String = ""

    @ManyToOne
    @JoinColumn(name="todo_id")
    var todo: JpaTodo? = null

    @Column(name="progress")
    var progress: Int = 0

    @Column(name="necessity")
    var necessity: Int = 0

    @Column(name="binary_progress")
    var binaryProgress: Boolean = false

    @Column(name="favorite")
    var favorite: Boolean = false

    @Column(columnDefinition="TEXT")
    var notes: String = ""



    constructor(skillTodo: SkillTodo) : this() {
        skillTodoId = skillTodo.skillTodoId
        progress = skillTodo.progress
        notes = skillTodo.notes
        skillName = skillTodo.skillName
        manualName = skillTodo.manualName
        necessity = skillTodo.necessity
        binaryProgress = skillTodo.binaryProgress
        favorite = skillTodo.favorite
        todo = skillTodo.todo?.let { JpaTodo(it, false) }
    }

    fun toSkillTodo() = SkillTodo().also {
        it.skillTodoId = skillTodoId
        it.progress = progress
        it.notes = notes
        it.necessity = necessity
        it.skillName = skillName
        it.manualName = manualName
        it.binaryProgress = binaryProgress
        it.favorite = favorite
        it.todo = Todo(todo?.todoId)
    }
}

