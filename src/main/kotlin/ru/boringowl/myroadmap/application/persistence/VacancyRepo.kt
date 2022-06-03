package ru.boringowl.myroadmap.application.persistence

import org.springframework.data.jpa.repository.JpaRepository
import ru.boringowl.myroadmap.infrastructure.jpa.JpaVacancy

interface VacancyRepo : JpaRepository<JpaVacancy, Int>

