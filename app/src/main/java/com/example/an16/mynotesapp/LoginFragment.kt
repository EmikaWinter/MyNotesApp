package com.example.an16.mynotesapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.an16.mynotesapp.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private var binding: FragmentLoginBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.loginToSignup?.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.container, SignupFragment())
                .commit()
        }

        val loginInput = binding?.loginEmailInputEdit
        val loginInputLayout = binding?.loginEmailInput

        val passwordInput = binding?.loginPasswordInputEdit
        val passwordInputLayout = binding?.loginPasswordInput

        val loginButton = binding?.loginButton

        val validator = Validator()

        loginButton?.setOnClickListener {
            val emailStatus = validator.validateEmail(loginInput?.text.toString())
            val passwordStatus = validator.validatePassword(passwordInput?.text.toString())

            loginInputLayout?.error = when (emailStatus) {
                Status.EMPTY_FIELD -> getString(R.string.email_cant_be_empty)
                Status.INCORRECT_FORMAT -> getString(R.string.email_incorrect)
                else -> ""
            }

            passwordInputLayout?.error = when (passwordStatus) {
                Status.EMPTY_FIELD -> getString(R.string.password_cant_be_empty)
                Status.INCORRECT_FORMAT -> getString(R.string.password_incorrect)
                else -> ""
            }

            if (loginInputLayout?.error.isNullOrEmpty() && passwordInputLayout?.error.isNullOrEmpty()) {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.container, AllNotesFragment())
                    .commit()
            }
        }
    }
}