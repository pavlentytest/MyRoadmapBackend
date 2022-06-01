package ru.boringowl.myroadmap.application.persistence

import org.springframework.data.jpa.repository.JpaRepository
import ru.boringowl.myroadmap.infrastructure.jpa.JpaSkill
import ru.boringowl.myroadmap.infrastructure.jpa.JpaVacancy
import java.util.*

interface VacancyRepo : JpaRepository<JpaVacancy, Int>

