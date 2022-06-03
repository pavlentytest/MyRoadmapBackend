package ru.boringowl.myroadmap.infrastructure.jpa

import ru.boringowl.myroadmap.domain.Route
import javax.persistence.*

@Entity
@Table(name="routes_table")
class JpaRoute() {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "route_id")
    var routeId: Int? = null

    @Column(name = "route_name")
    var routeName: String = ""

    @Column(columnDefinition="TEXT", name = "route_description")
    var routeDescription: String = ""

    @Column(name = "resumes_count")
    var resumesCount: Int = 0
    @Column(name = "vacancies_count")
    var vacanciesCount: Int = 0


    constructor(route: Route) : this() {
        routeId = route.routeId
        routeName = route.routeName
        routeDescription = route.routeDescription
        resumesCount = route.resumesCount
        vacanciesCount = route.vacanciesCount
    }

    fun toRoute() = Route().also {
        it.routeId = routeId
        it.routeName = routeName
        it.routeDescription = routeDescription
        it.resumesCount = resumesCount
        it.vacanciesCount = vacanciesCount
    }
}
