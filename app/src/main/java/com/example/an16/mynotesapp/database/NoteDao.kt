package com.example.an16.mynotesapp.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface NoteDao {

    @Insert
    suspend fun addNote(note: NoteEntity)

    @Delete
    suspend fun deleteNote(note: NoteEntity)

    @Update
    suspend fun update(note: NoteEntity)

    @Query("SELECT * FROM NoteEntity")
    suspend fun getAllNotes(): List<NoteEntity>

    @Query("SELECT * FROM NoteEntity WHERE id == :id LIMIT 1")
    suspend fun getNoteId(id: Int): NoteEntity?

    @Query("SELECT * FROM NoteEntity WHERE title LIKE '%' || :key || '%' OR text LIKE '%' || :key || '%'")
    fun getNotesByKeyword(key: String): List<NoteEntity>

    @Query("SELECT * FROM NoteEntity WHERE userId == :userId")
    suspend fun getNotesByUserId(userId: Int): List<NoteEntity>
}