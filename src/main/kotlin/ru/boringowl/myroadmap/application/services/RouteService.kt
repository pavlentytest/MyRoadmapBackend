package ru.boringowl.myroadmap.application.services

import org.springframework.stereotype.Service
import ru.boringowl.myroadmap.application.persistence.RouteRepo
import ru.boringowl.myroadmap.domain.Route
import ru.boringowl.myroadmap.infrastructure.jpa.JpaRoute

@Service
class RouteService(val routeRepo: RouteRepo) : BaseService<Route, JpaRoute, Int>(routeRepo) {
    override fun toJpa(dto: Route): JpaRoute? = JpaRoute(dto)
    override fun toDto(jpa: JpaRoute?): Route? = jpa?.toRoute()
    override fun getId(dto: Route): Int? = dto.routeId
}
