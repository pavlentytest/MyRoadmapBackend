package ru.boringowl.myroadmap.application.persistence

import org.springframework.data.repository.CrudRepository
import ru.boringowl.myroadmap.infrastructure.jpa.JpaRoute
import ru.boringowl.myroadmap.infrastructure.jpa.JpaSkill
import java.util.*

interface SkillRepo : CrudRepository<JpaSkill, UUID>

