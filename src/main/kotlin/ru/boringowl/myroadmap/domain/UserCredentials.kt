package ru.boringowl.myroadmap.domain

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
interface UserCredentials {
    val username: String
    val password: String
}
