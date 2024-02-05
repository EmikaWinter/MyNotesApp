package com.example.an16.mynotesapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.an16.mynotesapp.R
import com.example.an16.mynotesapp.repository.SharedPreferencesRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var sharedPreferencesRepository: SharedPreferencesRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        when {
            sharedPreferencesRepository.isFirstLaunch() -> {
                openFragment(FirstPageFragment())
                sharedPreferencesRepository.setIsFirstLaunch()
            }

            sharedPreferencesRepository.getUserEmail() == null -> {
                openFragment(LoginFragment())
            }

            else -> {
                openFragment(MainFragment())
            }
        }
    }

    private fun openFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }
}