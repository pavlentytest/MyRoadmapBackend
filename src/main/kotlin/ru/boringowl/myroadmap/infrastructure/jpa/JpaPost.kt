package ru.boringowl.myroadmap.infrastructure.jpa

import ru.boringowl.myroadmap.domain.Post
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
@Table(name="posts_table")
class JpaPost() {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "post_id")
    var postId: UUID? = null

    @Column(name = "post_name")
    var postName: String = ""

    @Column(columnDefinition="TEXT", name = "post_description")
    var postDescription: String = ""

    @Column(name = "date_from")
    var dateFrom: LocalDateTime = LocalDateTime.now()
    @Column(name = "date_to")
    var dateTo: LocalDateTime = LocalDateTime.now()

    @Column
    var source: String = ""

    @Column(name = "is_hackathon")
    var isHackathon: Boolean = false
    
    
    constructor(post: Post) : this() {
        postId = post.postId
        postName = post.postName
        postDescription = post.postDescription
        dateFrom = post.dateFrom
        dateTo = post.dateTo
        source = post.source
        isHackathon = post.isHackathon
    }

    fun toPost() = Post().also {
        it.postId = postId
        it.postName = postName
        it.postDescription = postDescription
        it.dateFrom = dateFrom
        it.dateTo = dateTo
        it.source = source
        it.isHackathon = isHackathon
    }
}
