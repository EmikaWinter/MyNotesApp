package com.example.an16.mynotesapp.ui.addnote

import androidx.lifecycle.ViewModel
import com.example.an16.mynotesapp.repository.NoteRepository

class AddNoteViewModel : ViewModel() {

    private val repository = NoteRepository()

    fun addNote(title: String, text: String) {
        repository.addNote(title, text)
    }

}