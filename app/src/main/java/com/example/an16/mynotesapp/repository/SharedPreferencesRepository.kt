package com.example.an16.mynotesapp.repository

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

private const val SHARED_PREF = "sharedPref"
private const val USER_PREF = "userPref"
private const val IS_FIRST_LAUNCH = "is_first_launch"
private const val USER_EMAIL = "user_email"
private const val USER_ID = "user_id"

@Singleton
class SharedPreferencesRepository @Inject constructor(@ApplicationContext context: Context) {

    private val sharedPreferences: SharedPreferences
    private val userPreferences: SharedPreferences

    init {
        sharedPreferences = context.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE)
        userPreferences = context.getSharedPreferences(USER_PREF, Context.MODE_PRIVATE)
    }

    fun isFirstLaunch(): Boolean {
        return sharedPreferences.getBoolean(IS_FIRST_LAUNCH, true)
    }

    fun setIsFirstLaunch() {
        sharedPreferences.edit {
            putBoolean(IS_FIRST_LAUNCH, false)
        }
    }

    fun setUserEmail(email: String) {
        userPreferences.edit {
            putString(USER_EMAIL, email)
        }
    }

    fun getUserEmail(): String? {
        return userPreferences.getString(USER_EMAIL, null)
    }

    fun setUserId(id: Int) {
        userPreferences.edit {
            putInt(USER_ID, id)
        }
    }

    fun getUserId(): Int {
        return userPreferences.getInt(USER_ID, 0)
    }

    fun logout() {
        userPreferences.edit {
            clear()
        }
    }
}