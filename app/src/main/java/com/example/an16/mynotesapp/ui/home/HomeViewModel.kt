package com.example.an16.mynotesapp.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.an16.mynotesapp.model.Note
import com.example.an16.mynotesapp.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: NoteRepository
) : ViewModel() {

    val listNote = MutableLiveData<ArrayList<Note>>()
    val note = MutableLiveData<Note>()


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