package com.example.an16.mynotesapp.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.an16.mynotesapp.repository.SharedPreferencesRepository
import com.example.an16.mynotesapp.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val sharedPreferencesRepository: SharedPreferencesRepository
) : ViewModel() {

    var openMainFragment: (() -> Unit)? = null

    fun addUser(email: String) {
        sharedPreferencesRepository.setUserEmail(email)
        viewModelScope.launch(Dispatchers.IO) {
            val id = userRepository.addUser(email)
            sharedPreferencesRepository.setUserId(id.toInt())
            openMainFragment?.invoke()
        }
    }
}