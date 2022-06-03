package ru.boringowl.myroadmap.infrastructure.parsing

import com.google.gson.annotations.SerializedName

data class Vacancy(
    @SerializedName("id")
    val id: String = "",
    @SerializedName("name")
    val name: String = "",
    @SerializedName("salary")
    val salary: Salary?,
    @SerializedName("alternate_url")
    val alternateUrl: String = "",
    @SerializedName("description")
    val description: String? = "",
    @SerializedName("key_skills")
    val skills: List<Skill>? = arrayListOf()
) {
    data class Skill(
        @SerializedName("name")
        val name: String = ""
    )

    data class Salary(
        @SerializedName("from")
        val from: Int? = null,
        @SerializedName("currency")
        val currency: String = "",
        @SerializedName("to")
        val to: Int? = null
    )
}
