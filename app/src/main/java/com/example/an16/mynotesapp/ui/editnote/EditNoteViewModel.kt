package com.example.an16.mynotesapp.ui.editnote

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.an16.mynotesapp.model.Note
import com.example.an16.mynotesapp.repository.NoteRepository

class EditNoteViewModel : ViewModel() {

    val note = MutableLiveData<Note>()
    private val repository = NoteRepository()

    fun getNoteById(id: Int) {
       note.value = repository.getNoteById(id)
    }

    fun editNote(title: String, text: String) {
        note.value?.copy(title = title, text = text)?.let {
            repository.updateNote(it)
        }
    }
}