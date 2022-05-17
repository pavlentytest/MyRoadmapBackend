package ru.boringowl.myroadmap.infrastructure.jpa

import javax.persistence.Entity
import javax.persistence.Table

import org.hibernate.annotations.NaturalId
import ru.boringowl.myroadmap.domain.User
import ru.boringowl.myroadmap.domain.UserRole
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "users_table")
class JpaUser() {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    var userId: UUID? = null

    @NaturalId
    @Column(unique = true)
    var username: String = ""

    @Column(unique = true)
    var email: String = ""

    @Column
    var role: UserRole = UserRole.User

    @Column
    var password = ""

    constructor(user: User) : this() {
        userId = user.userId
        username = user.username
        role = user.role
    }

    fun toUser() = User(userId, username, email, role)
}
