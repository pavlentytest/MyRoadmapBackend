package ru.boringowl.myroadmap.application.services

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Service
import ru.boringowl.myroadmap.application.persistence.PostRepo
import ru.boringowl.myroadmap.application.persistence.UserRepo
import ru.boringowl.myroadmap.domain.Post
import ru.boringowl.myroadmap.domain.User
import ru.boringowl.myroadmap.infrastructure.jpa.JpaPost
import java.util.*

@Service
class PostService(val postRepo: PostRepo) : BaseService<Post, JpaPost, UUID>(postRepo) {
    override fun toJpa(dto: Post): JpaPost? = JpaPost(dto)
    override fun toDto(jpa: JpaPost?): Post? = jpa?.toPost()
    override fun getId(dto: Post): UUID? = dto.postId
}
