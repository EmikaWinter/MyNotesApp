package com.example.an16.mynotesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class SignupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        findViewById<TextView>(R.id.signup_to_login).setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        val firstNameInput = findViewById<TextInputEditText>(R.id.signup_firstname_input_edit)
        val firstNameInputLayout = findViewById<TextInputLayout>(R.id.signup_firstname_input)

        val lastNameInput = findViewById<TextInputEditText>(R.id.signup_lastname_input_edit)
        val lastNameInputLayout = findViewById<TextInputLayout>(R.id.signup_lastname_input)

        val emailInput = findViewById<TextInputEditText>(R.id.signup_email_input_edit)
        val emailInputLayout = findViewById<TextInputLayout>(R.id.signup_email_input)

        val passwordInput = findViewById<TextInputEditText>(R.id.signup_password_input_edit)
        val passwordInputLayout = findViewById<TextInputLayout>(R.id.signup_password_input)

        val signupButton = findViewById<Button>(R.id.signup_button)

        val validator = Validator()

        signupButton.setOnClickListener {
            val firstNameStatus = validator.validateName(firstNameInput.text.toString())
            val lastNameStatus = validator.validateName(lastNameInput.text.toString())
            val emailStatus = validator.validateEmail(emailInput.text.toString())
            val passwordStatus = validator.validatePassword(passwordInput.text.toString())

            firstNameInputLayout.error = when (firstNameStatus) {
                Status.EMPTY_FIELD -> getString(R.string.name_empty_field)
                Status.INCORRECT_FORMAT -> getString(R.string.name_3_to_255_field)
                else -> ""
            }

            lastNameInputLayout.error = when (lastNameStatus) {
                Status.EMPTY_FIELD -> getString(R.string.name_empty_field)
                Status.INCORRECT_FORMAT -> getString(R.string.name_3_to_255_field)
                else -> ""
            }

            emailInputLayout.error = when (emailStatus) {
                Status.EMPTY_FIELD -> getString(R.string.email_cant_be_empty)
                Status.INCORRECT_FORMAT -> getString(R.string.email_incorrect)
                else -> ""
            }

            passwordInputLayout.error = when (passwordStatus) {
                Status.EMPTY_FIELD -> getString(R.string.password_cant_be_empty)
                Status.INCORRECT_FORMAT -> getString(R.string.password_incorrect)
                else -> ""
            }

            if (firstNameInputLayout.error.isNullOrEmpty() &&
                lastNameInputLayout.error.isNullOrEmpty() &&
                emailInputLayout.error.isNullOrEmpty() &&
                passwordInputLayout.error.isNullOrEmpty()
            ) {
                val intent = Intent(this, ViewNotesActivity::class.java)
                startActivity(intent)
            }
        }
    }
}