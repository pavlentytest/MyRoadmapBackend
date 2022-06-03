package ru.boringowl.myroadmap.domain

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
public enum class UserRole {
    User,
    Moderator,
    Administrator,
}
