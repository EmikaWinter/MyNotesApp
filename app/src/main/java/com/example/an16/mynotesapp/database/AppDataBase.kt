package com.example.an16.mynotesapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.an16.mynotesapp.util.DateTypeConverter

@Database(entities = [NoteEntity::class], version = 1)
@TypeConverters(value = [DateTypeConverter::class])
abstract class AppDataBase: RoomDatabase() {

    abstract fun getNoteDao(): NoteDao
}