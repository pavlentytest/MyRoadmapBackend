package ru.boringowl.myroadmap.application.persistence

import org.springframework.data.repository.CrudRepository
import ru.boringowl.myroadmap.infrastructure.jpa.JpaPost
import java.util.*

interface PostRepo : CrudRepository<JpaPost, UUID>
