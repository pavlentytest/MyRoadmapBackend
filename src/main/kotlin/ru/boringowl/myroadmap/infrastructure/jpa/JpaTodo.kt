package ru.boringowl.myroadmap.infrastructure.jpa

import ru.boringowl.myroadmap.domain.Todo
import javax.persistence.Entity
import javax.persistence.Table

import java.util.*
import javax.persistence.*

@Entity
@Table(name="todo_table")
class JpaTodo() {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="todo_id")
    var todoId: UUID? = null

    @Column(name="todo_header")
    var header = ""

    @ManyToOne
    var user: JpaUser? = null

    @OneToMany(fetch = FetchType.EAGER, cascade= [CascadeType.ALL])
    @JoinColumn(name="todo_id")
    var skills: List<JpaSkillTodo>? = listOf()



    constructor(todo: Todo, skillsNeeded: Boolean = true) : this() {
        todoId = todo.todoId
        header = todo.header
        user = todo.user?.let { JpaUser(it) }
        if (skillsNeeded)
            skills = todo.skills?.map { JpaSkillTodo(it) }
    }

    fun toTodo(includeSkills: Boolean = true) = Todo().also {
        it.todoId = todoId
        it.header = header
        if (includeSkills)
            it.skills = skills?.map {s -> s.toSkillTodo() }
    }
}
