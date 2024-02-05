package com.example.an16.mynotesapp.repository

import com.example.an16.mynotesapp.database.NoteDao
import com.example.an16.mynotesapp.database.NoteEntity
import com.example.an16.mynotesapp.model.Note
import java.util.Date
import javax.inject.Inject

class NoteRepository @Inject constructor(
    private val noteDao: NoteDao,
    private val sharedPreferencesRepository: SharedPreferencesRepository
) {

    suspend fun getNoteList(): ArrayList<Note> {
        return (noteDao.getNotesByUserId(sharedPreferencesRepository.getUserId()).map { note ->
            Note(note.id, note.title, note.text, note.date)
        } as? ArrayList<Note>) ?: arrayListOf()
    }

    suspend fun getNoteById(id: Int): Note? {
        noteDao.getNoteId(id)?.let { note ->
            return Note(note.id, note.title, note.text, note.date)
        } ?: run {
            return null
        }
    }

    suspend fun getNotesByKeyword(key: String): ArrayList<Note> {
        return (noteDao.getNotesByKeyword(key).map { note ->
            Note(note.id, note.title, note.text, note.date)
        } as? ArrayList<Note>) ?: arrayListOf()
    }

    suspend fun addNote(title: String, text: String) {
        noteDao.addNote(NoteEntity(0, title, text, Date(), sharedPreferencesRepository.getUserId()))
    }

    suspend fun deleteNote(id: Int) {
        noteDao.deleteNote(NoteEntity(id, "", "", Date(), sharedPreferencesRepository.getUserId()))
    }

    suspend fun updateNote(note: Note) {
        noteDao.update(
            NoteEntity(
                note.id,
                note.title,
                note.text,
                note.date,
                sharedPreferencesRepository.getUserId()
            )
        )
    }
}