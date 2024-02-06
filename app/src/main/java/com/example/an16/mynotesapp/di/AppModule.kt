package com.example.an16.mynotesapp.di

import android.content.Context
import androidx.room.Room
import com.example.an16.mynotesapp.database.AppDataBase
import com.example.an16.mynotesapp.database.NoteDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideDB(@ApplicationContext context: Context): AppDataBase {
        return Room.databaseBuilder(context, AppDataBase::class.java, "dataBase")
            .allowMainThreadQueries()
            .build()
    }

    @Singleton
    @Provides
    fun provideNoteDao(appDataBase: AppDataBase): NoteDao {

        return appDataBase.getNoteDao()
    }
}