
package com.example.formvalidation.validator

object FormValidator {

    private val EMAIL_REGEX =
        Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")

    private val PHONE_REGEX =
        Regex("^(03|05|07|08|09)\\d{8}$")

    fun isValidName(name: String): Boolean {
        return name.trim().length >= 3
    }

    fun isValidEmail(email: String): Boolean {
        return email.isNotBlank() &&
                EMAIL_REGEX.matches(email.trim())
    }

    fun isValidPassword(pass: String): Boolean {
        return pass.length >= 6
    }

    fun isValidPhone(phone: String): Boolean {
        return PHONE_REGEX.matches(phone.trim())
    }
}
