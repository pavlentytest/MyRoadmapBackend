package ru.boringowl.myroadmap.application.persistence

import org.springframework.data.repository.CrudRepository
import ru.boringowl.myroadmap.infrastructure.jpa.JpaSkill
import ru.boringowl.myroadmap.infrastructure.jpa.JpaVacancy
import java.util.*

interface VacancyRepo : CrudRepository<JpaVacancy, Int>

