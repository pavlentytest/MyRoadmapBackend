package ru.boringowl.myroadmap.infrastructure.jpa

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
    var id: UUID? = null

    @NaturalId
    @Column(unique = true)
    var username: String = ""

    @Column(unique = true)
    var email: String = ""

    @Column
    var role: UserRole = UserRole.User

    @Column
    var password = ""

    @Column
    var location: String = "Россия, Москва"

    @Column
    var companyRole: String = "Не указано"

    @Column
    var rating: Int = 0

    @Column
    var description: String = ""

    @Column
    var photoUrl: String = "https://i.imgur.com/ERuVfLM.jpeg"

    constructor(user: User) : this() {
        id = user.id
        username = user.username
        role = user.role
        location = user.location
        companyRole = user.companyRole
        rating = user.rating
        description = user.description
        photoUrl = user.photoUrl
    }

    fun toUser() = User(id!!, username, email, location, companyRole, rating, description, photoUrl, role)
}
