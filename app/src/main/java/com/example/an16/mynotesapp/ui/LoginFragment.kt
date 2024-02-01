package com.example.an16.mynotesapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.an16.mynotesapp.R
import com.example.an16.mynotesapp.Status
import com.example.an16.mynotesapp.Validator
import com.example.an16.mynotesapp.databinding.FragmentLoginBinding
import com.example.an16.mynotesapp.repository.SharedPreferencesRepository

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

        val validator = Validator()

        binding?.loginButton?.setOnClickListener {

            val email = binding?.loginEmailInputEdit?.text.toString()
            SharedPreferencesRepository.setUserEmail(email)

            val emailStatus = validator.validateEmail(email)
            val passwordStatus = validator.validatePassword(binding?.loginPasswordInputEdit?.text.toString())

            binding?.loginEmailInput?.error = when (emailStatus) {
                Status.EMPTY_FIELD -> getString(R.string.email_cant_be_empty)
                Status.INCORRECT_FORMAT -> getString(R.string.email_incorrect)
                else -> ""
            }

            binding?.loginPasswordInput?.error = when (passwordStatus) {
                Status.EMPTY_FIELD -> getString(R.string.password_cant_be_empty)
                Status.INCORRECT_FORMAT -> getString(R.string.password_incorrect)
                else -> ""
            }

            if (binding?.loginEmailInput?.error.isNullOrEmpty() && binding?.loginPasswordInput?.error.isNullOrEmpty()) {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.container, MainFragment())
                    .commit()
            }
        }
    }
}