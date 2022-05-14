package ru.boringowl.myroadmap.infrastructure.parsing

import com.google.gson.annotations.SerializedName

class VacancyResponse(
    @SerializedName("items")
    val items: List<Vacancy>
)
