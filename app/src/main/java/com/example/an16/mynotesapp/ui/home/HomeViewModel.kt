package com.example.an16.mynotesapp.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.an16.mynotesapp.model.Note
import com.example.an16.mynotesapp.repository.NoteRepository

class HomeViewModel : ViewModel() {

    val listNote = MutableLiveData<ArrayList<Note>>()
    val note = MutableLiveData<Note>()

    private val repository = NoteRepository()

    fun loadListNote() {
        listNote.value = repository.getNoteList()
    }

    fun deleteNote(id: Int) {
        repository.deleteNote(id)
        loadListNote()
    }

    fun getNoteById(id: Int): Note? {
        return repository.getNoteById(id)
    }

}