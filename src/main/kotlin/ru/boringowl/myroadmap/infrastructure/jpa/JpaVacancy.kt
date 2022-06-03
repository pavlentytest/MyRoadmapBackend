package ru.boringowl.myroadmap.infrastructure.jpa

import ru.boringowl.myroadmap.domain.Vacancy
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name="vacancy_table")
class JpaVacancy() {
    @Id
    @Column(name = "vacancy_id")
    var vacancyId: Int? = null

    @Column(name = "vacancy_name")
    var vacancyName: String = ""

    @Column(columnDefinition="TEXT", name = "vacancy_description")
    var vacancyDescription: String = ""

    @Column(name = "salary_from")
    var salaryFrom: Int = 0
    @Column(name = "salary_to")
    var salaryTo: Int = 0

    @Column
    var company: String = ""


    constructor(vacancy: Vacancy) : this() {
        vacancyId = vacancy.vacancyId
        vacancyName = vacancy.vacancyName
        vacancyDescription = vacancy.vacancyDescription
        salaryFrom = vacancy.salaryFrom
        salaryTo = vacancy.salaryTo
        company = vacancy.company
    }

    fun toVacancy() = Vacancy().also {
        it.vacancyId = vacancyId
        it.vacancyName = vacancyName
        it.vacancyDescription = vacancyDescription
        it.salaryFrom = salaryFrom
        it.salaryTo = salaryTo
        it.company = company
    }
}
