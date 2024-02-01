package com.example.an16.mynotesapp

import android.app.Application
import com.example.an16.mynotesapp.database.DataBase
import com.example.an16.mynotesapp.repository.SharedPreferencesRepository

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        SharedPreferencesRepository.init(applicationContext)

        DataBase.init(applicationContext)
    }
}