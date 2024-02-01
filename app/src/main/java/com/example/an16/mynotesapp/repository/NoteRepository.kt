package com.example.an16.mynotesapp.repository

import com.example.an16.mynotesapp.database.DataBase
import com.example.an16.mynotesapp.database.NoteEntity
import com.example.an16.mynotesapp.model.Note
import java.util.Date

class NoteRepository {

    fun getNoteList(): ArrayList<Note> {
        return (DataBase.noteDao?.getAllNotes()?.map { note ->
            Note(note.id, note.title, note.text, note.date)
        } as? ArrayList<Note>) ?: arrayListOf()
    }

    fun getNoteById(id: Int): Note? {
        DataBase.noteDao?.getNoteId(id)?.let { note ->
            return Note(note.id, note.title, note.text, note.date)
        } ?: run {
            return null
        }
    }

    fun addNote(title: String, text: String) {
        DataBase.noteDao?.addNote(NoteEntity(0, title, text, Date()))
    }

    fun deleteNote(note: Note) {
        DataBase.noteDao?.deleteNote(NoteEntity(note.id, note.title, note.text, note.date))
    }

    fun deleteNote(id: Int) {
        DataBase.noteDao?.deleteNote(NoteEntity(id, "", "", Date()))
    }

    fun updateNote(note: Note) {
        DataBase.noteDao?.update(NoteEntity(note.id, note.title, note.text, note.date))
    }
}