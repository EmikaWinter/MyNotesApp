package com.example.an16.mynotesapp.database

import android.content.Context
import androidx.room.Room

object DataBase {

    var noteDao: NoteDao? = null
    fun init(context: Context) {
        val dataBase: AppDataBase =
            Room.databaseBuilder(context,AppDataBase::class.java, "dataBase")
                .allowMainThreadQueries()
                .build()
        noteDao = dataBase.getNoteDao()
    }
}