package com.example.an16.mynotesapp

import java.util.regex.Pattern

class Validator {

    fun validateEmail(editText: String): Status {
        return when {
            editText.isEmpty() -> Status.EMPTY_FIELD
            !isEmailValid(editText) -> Status.INCORRECT_FORMAT
            else -> Status.CORRECT
        }
    }

    fun validatePassword(editText: String): Status{
        return when {
            editText.isEmpty() -> Status.EMPTY_FIELD
            !isPasswordValid(editText) -> Status.INCORRECT_FORMAT
            else -> Status.CORRECT
        }
    }

    private fun isEmailValid(email: String): Boolean {
        val emailRegex = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})$"
        val pattern = Pattern.compile(emailRegex)
        val matcher = pattern.matcher(email)
        return matcher.matches()
    }

    private fun isPasswordValid(password: String): Boolean {
        val passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!])(?=\\S+\$).{6,50}\$"
        return password.matches(passwordRegex.toRegex())
    }


}

enum class Status {
    CORRECT,
    EMPTY_FIELD,
    INCORRECT_FORMAT,

}