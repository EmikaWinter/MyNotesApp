package com.example.an16.mynotesapp.repository

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

private const val SHARED_PREF = "sharedPref"
private const val USER_PREF = "userPref"
private const val IS_FIRST_LAUNCH = "is_first_launch"
private const val USER_EMAIL = "user_email"

object SharedPreferencesRepository {

    private var sharedPreferences: SharedPreferences? = null
    private var userPreferences: SharedPreferences? = null
    fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE)
        userPreferences = context.getSharedPreferences(USER_PREF, Context.MODE_PRIVATE)
    }

    fun isFirstLaunch(): Boolean {
        return sharedPreferences?.getBoolean(IS_FIRST_LAUNCH, true) ?: true
    }

    fun setIsFirstLaunch() {
        sharedPreferences?.edit {
            putBoolean(IS_FIRST_LAUNCH, false)
        }
    }

    fun setUserEmail(email: String) {
        userPreferences?.edit{
            putString(USER_EMAIL, email)
        }
    }

    fun getUserEmail(): String? {
        return userPreferences?.getString(USER_EMAIL, null)
    }

    fun logout(){
        userPreferences?.edit{
            clear()
        }
    }
}