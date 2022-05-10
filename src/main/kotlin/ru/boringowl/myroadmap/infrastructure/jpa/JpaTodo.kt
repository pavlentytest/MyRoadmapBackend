package ru.boringowl.myroadmap.infrastructure.jpa

import ru.boringowl.myroadmap.domain.SkillTodo
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



    constructor(todo: Todo) : this() {
        todoId = todo.todoId
        header = todo.header
        user = todo.user?.let { JpaUser(it) }
    }

    fun toTodo() = Todo().also {
        it.todoId = todoId
        it.header = header
        it.user = user?.toUser()
    }
}
