package com.example.an16.mynotesapp.util

import java.util.regex.Pattern

private const val MIN_NAME_LENGTH: Int = 3
private const val MAX_NAME_LENGTH: Int = 255

class Validator {

    fun validateName(editText: String): Status {
        return when {
            editText.isBlank() -> Status.EMPTY_FIELD
            editText.length < MIN_NAME_LENGTH || editText.length > MAX_NAME_LENGTH -> Status.INCORRECT_FORMAT
            else -> Status.CORRECT
        }
    }

    fun validateEmail(editText: String): Status {
        return when {
            editText.isBlank() -> Status.EMPTY_FIELD
            !isEmailValid(editText) -> Status.INCORRECT_FORMAT
            else -> Status.CORRECT
        }
    }

    fun validatePassword(editText: String): Status {
        return when {
            editText.isBlank() -> Status.EMPTY_FIELD
            !isPasswordValid(editText) -> Status.INCORRECT_FORMAT
            else -> Status.CORRECT
        }
    }

    fun validateText(editText: String): Boolean {
        if (editText.isBlank()) {
            return false
        }
        return true
    }

    private fun isEmailValid(email: String): Boolean {
        val emailRegex = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})$"
        val pattern = Pattern.compile(emailRegex)
        val matcher = pattern.matcher(email)
        return matcher.matches()
    }

    private fun isPasswordValid(password: String): Boolean {
        val passwordRegex =
            "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!;:?_-])(?=\\S+\$).{6,50}\$"
        return password.matches(passwordRegex.toRegex())
    }
}

enum class Status {
    CORRECT,
    EMPTY_FIELD,
    INCORRECT_FORMAT,
}