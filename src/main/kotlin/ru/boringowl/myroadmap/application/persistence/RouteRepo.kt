package ru.boringowl.myroadmap.application.persistence

import org.springframework.data.repository.CrudRepository
import ru.boringowl.myroadmap.infrastructure.jpa.JpaPost
import ru.boringowl.myroadmap.infrastructure.jpa.JpaRoute
import java.util.*

interface RouteRepo : CrudRepository<JpaRoute, UUID>
