package com.example.an16.mynotesapp.ui.allnotes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.an16.mynotesapp.model.Note
import com.example.an16.mynotesapp.repository.NoteRepository

class AllNotesViewModel: ViewModel() {

    val listNote = MutableLiveData<ArrayList<Note>>()
    val note = MutableLiveData<Note>()

    private val repository = NoteRepository()

    fun loadListNote(){
        listNote.value = repository.getNoteList()
    }

    fun deleteNote(){
        note.value?.let {
            repository.deleteNote(it)
        }
    }

}