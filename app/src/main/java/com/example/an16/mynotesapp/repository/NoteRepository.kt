package com.example.an16.mynotesapp.repository

import com.example.an16.mynotesapp.database.NoteDao
import com.example.an16.mynotesapp.database.NoteEntity
import com.example.an16.mynotesapp.model.Note
import java.util.Date
import javax.inject.Inject

class NoteRepository @Inject constructor(
    private val noteDao: NoteDao
){

    fun getNoteList(): ArrayList<Note> {
        return (noteDao.getAllNotes().map { note ->
            Note(note.id, note.title, note.text, note.date)
        } as? ArrayList<Note>) ?: arrayListOf()
    }

    fun getNoteById(id: Int): Note? {
        noteDao.getNoteId(id)?.let { note ->
            return Note(note.id, note.title, note.text, note.date)
        } ?: run {
            return null
        }
    }

    fun addNote(title: String, text: String) {
        noteDao.addNote(NoteEntity(0, title, text, Date()))
    }

    fun deleteNote(note: Note) {
        noteDao.deleteNote(NoteEntity(note.id, note.title, note.text, note.date))
    }

    fun deleteNote(id: Int) {
        noteDao.deleteNote(NoteEntity(id, "", "", Date()))
    }

    fun updateNote(note: Note) {
        noteDao.update(NoteEntity(note.id, note.title, note.text, note.date))
    }
}