package ru.boringowl.myroadmap.infrastructure.utils

object StringUtils {
    fun checkUsername(username: String) {
        require(username.length > 5) {"Имя пользователя должно содержать не менее 6 символов"}
        require(Regex("\\w*").matches(username)) {"Имя пользователя не валидно"}
    }
    fun checkPassword(password: String) {
        require(password.length > 7) {"Пароль должен содержать не менее 8 символов"}
        require(Regex("([\\w!,\\(\\)-])*").matches(password)) {"Пароль не валиден"}
    }
    fun checkEmail(email: String) {
        require(Regex("[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}").matches(email)) {"Email не валиден"}
    }
    fun checkUserInfo(username: String, password: String, email: String) {
        checkUsername(username)
        checkPassword(password)
        checkEmail(email)
    }
}
