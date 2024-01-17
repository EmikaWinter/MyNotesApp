package com.example.an16.mynotesapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        findViewById<TextView>(R.id.signup_to_login).setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }

        val loginInput = findViewById<TextInputEditText>(R.id.login_email_input_edit)
        val loginInputLayout = findViewById<TextInputLayout>(R.id.login_email_input)

        val passwordInput = findViewById<TextInputEditText>(R.id.login_password_input_edit)
        val passwordInputLayout = findViewById<TextInputLayout>(R.id.login_password_input)

        val loginButton = findViewById<Button>(R.id.login_button)

        val validator = Validator()

        loginButton.setOnClickListener {
            val emailStatus = validator.validateEmail(loginInput.text.toString())
            val passwordStatus = validator.validatePassword(passwordInput.text.toString())

            loginInputLayout.error = when (emailStatus) {
                Status.EMPTY_FIELD -> getString(R.string.email_cant_be_empty)
                Status.INCORRECT_FORMAT -> getString(R.string.email_incorrect)
                else -> ""
            }

            passwordInputLayout.error = when (passwordStatus) {
                Status.EMPTY_FIELD -> getString(R.string.password_cant_be_empty)
                Status.INCORRECT_FORMAT -> getString(R.string.password_incorrect)
                else -> ""
            }

            if (loginInputLayout.error.isNullOrEmpty() && passwordInputLayout.error.isNullOrEmpty()) {
                val intent = Intent(this, AllNotesActivity::class.java)
                startActivity(intent)
            }
        }
    }
}