package ru.boringowl.myroadmap.application.services

import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

abstract class  BaseService<D : Any, E : Any, ID : Any>(val repo: JpaRepository<E, ID>) {
    open fun get(): List<D> =
        repo.findAll().toList().map { toDto(it)!! }
    open fun get(pageable: Pageable): Page<D> =
        repo.findAll(pageable).map { toDto(it)!! }

    open fun get(id: ID): D? =
        toDto(repo.findById(id).orElse(null))

    open fun add(dto: D): D? {
        require(!isExists(getId(dto))) {"Запись уже существует"}
        return toDto(repo.save(toJpa(dto)!!))
    }

    open fun addOrUpdate(dto: D): D? {
        return toDto(repo.save(toJpa(dto)!!))
    }

    open fun update(dto: D): D? {
        require(isExists(getId(dto))) {"Запись отсутствует"}
        return toDto(repo.save(toJpa(dto)!!))
    }

    open fun delete(id: ID) {
        var entity = repo.findById(id).orElse(null)
        require(entity != null) { "Запись с таким id не найдена" }
        repo.delete(entity)
    }

    open fun isExists(id: ID?): Boolean = id != null && repo.existsById(id)

    abstract fun toJpa(dto: D) : E?
    abstract fun toDto(jpa: E?) : D?
    abstract fun getId(dto: D) : ID?
}
