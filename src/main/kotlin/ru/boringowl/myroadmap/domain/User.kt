package ru.boringowl.myroadmap.domain

import com.fasterxml.jackson.annotation.JsonInclude
import java.util.*

@JsonInclude(JsonInclude.Include.NON_NULL)
class User(
    val id: UUID?, //NanoId
    val username: String,
    val email: String,
    val location: String,
    val companyRole: String,
    val rating: Int,
    val description: String,
    val photoUrl: String,
    var role: UserRole,
)

