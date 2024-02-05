package com.example.an16.mynotesapp.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface NoteDao {

    @Insert
    fun addNote(note: NoteEntity)

    @Delete
    fun deleteNote(note: NoteEntity)

    @Update
    fun update(note: NoteEntity)

    @Query("SELECT * FROM NoteEntity")
    fun getAllNotes(): List<NoteEntity>

    @Query("SELECT * FROM NoteEntity WHERE id == :id LIMIT 1")
    fun getNoteId(id: Int):NoteEntity?
}