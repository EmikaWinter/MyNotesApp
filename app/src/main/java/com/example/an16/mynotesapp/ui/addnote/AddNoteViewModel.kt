package com.example.an16.mynotesapp.ui.addnote

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.an16.mynotesapp.model.Note
import com.example.an16.mynotesapp.repository.NoteRepository

class AddNoteViewModel : ViewModel() {

//    val note = MutableLiveData<Note>()

    private val repository = NoteRepository()

//    fun getNoteById(id: Int) {
//        repository.getNoteById(id)?.let {
//            note.value = it
//        }
//    }

    fun getListNote() = repository.getNoteList()

    fun addNote(title: String, text: String) {
        repository.addNote(title, text)
    }

}