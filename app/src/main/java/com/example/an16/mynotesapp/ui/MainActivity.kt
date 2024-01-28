package com.example.an16.mynotesapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.an16.mynotesapp.R
import com.example.an16.mynotesapp.repository.SharedPreferencesRepository

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        when {
            SharedPreferencesRepository.isFirstLaunch() -> {
                openFragment(FirstPageFragment())
                SharedPreferencesRepository.setIsFirstLaunch()
            }

            SharedPreferencesRepository.getUserEmail() == null -> {
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