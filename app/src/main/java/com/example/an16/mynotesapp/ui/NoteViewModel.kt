package com.example.an16.mynotesapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.an16.mynotesapp.model.Note
import com.example.an16.mynotesapp.repository.NoteRepository

class NoteViewModel : ViewModel() {

    val note = MutableLiveData<Note>()

    private val repository = NoteRepository()

    fun getItemById(id: Int) {
        repository.getNoteById(id)?.let {
            note.value = it
        }
    }
}