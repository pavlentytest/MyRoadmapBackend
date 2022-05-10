package ru.boringowl.myroadmap.application.services

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Service
import ru.boringowl.myroadmap.application.persistence.PostRepo
import ru.boringowl.myroadmap.application.persistence.UserRepo
import ru.boringowl.myroadmap.application.persistence.VacancyRepo
import ru.boringowl.myroadmap.domain.Post
import ru.boringowl.myroadmap.domain.User
import ru.boringowl.myroadmap.domain.Vacancy
import ru.boringowl.myroadmap.infrastructure.jpa.JpaPost
import ru.boringowl.myroadmap.infrastructure.jpa.JpaVacancy
import java.util.*

@Service
class VacancyService(val vacancyRepo: VacancyRepo) : BaseService<Vacancy, JpaVacancy, Int>(vacancyRepo) {
    override fun toJpa(dto: Vacancy): JpaVacancy? = JpaVacancy(dto)
    override fun toDto(jpa: JpaVacancy?): Vacancy? = jpa?.toVacancy()
    override fun getId(dto: Vacancy): Int? = dto.vacancyId
}
