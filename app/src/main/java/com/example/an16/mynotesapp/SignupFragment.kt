package com.example.an16.mynotesapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.an16.mynotesapp.databinding.FragmentSignupBinding

class SignupFragment : Fragment() {

    private var binding: FragmentSignupBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignupBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.signupToLogin?.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.container, LoginFragment())
                .commit()
        }

        val firstNameInput = binding?.signupFirstnameInputEdit
        val firstNameInputLayout = binding?.signupFirstnameInput

        val lastNameInput = binding?.signupLastnameInputEdit
        val lastNameInputLayout = binding?.signupLastnameInput

        val emailInput = binding?.signupEmailInputEdit
        val emailInputLayout = binding?.signupEmailInput

        val passwordInput = binding?.signupPasswordInputEdit
        val passwordInputLayout = binding?.signupPasswordInput

        val signupButton = binding?.signupButton

        val validator = Validator()

        signupButton?.setOnClickListener {
            val firstNameStatus = validator.validateName(firstNameInput?.text.toString())
            val lastNameStatus = validator.validateName(lastNameInput?.text.toString())
            val emailStatus = validator.validateEmail(emailInput?.text.toString())
            val passwordStatus = validator.validatePassword(passwordInput?.text.toString())

            firstNameInputLayout?.error = when (firstNameStatus) {
                Status.EMPTY_FIELD -> getString(R.string.name_empty_field)
                Status.INCORRECT_FORMAT -> getString(R.string.name_3_to_255_field)
                else -> ""
            }

            lastNameInputLayout?.error = when (lastNameStatus) {
                Status.EMPTY_FIELD -> getString(R.string.name_empty_field)
                Status.INCORRECT_FORMAT -> getString(R.string.name_3_to_255_field)
                else -> ""
            }

            emailInputLayout?.error = when (emailStatus) {
                Status.EMPTY_FIELD -> getString(R.string.email_cant_be_empty)
                Status.INCORRECT_FORMAT -> getString(R.string.email_incorrect)
                else -> ""
            }

            passwordInputLayout?.error = when (passwordStatus) {
                Status.EMPTY_FIELD -> getString(R.string.password_cant_be_empty)
                Status.INCORRECT_FORMAT -> getString(R.string.password_incorrect)
                else -> ""
            }

            if (firstNameInputLayout?.error.isNullOrEmpty() &&
                lastNameInputLayout?.error.isNullOrEmpty() &&
                emailInputLayout?.error.isNullOrEmpty() &&
                passwordInputLayout?.error.isNullOrEmpty()
            ) {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.container, AllNotesFragment())
                    .commit()
            }
        }
    }
}