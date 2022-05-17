package ru.boringowl.myroadmap.domain

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)

class User(
    var userId: UUID? = null,
    var username: String = "",
    var email: String = "",
    var role: UserRole,
)
