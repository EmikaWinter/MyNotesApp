package com.example.an16.mynotesapp.ui.addnote

import androidx.lifecycle.ViewModel
import com.example.an16.mynotesapp.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddNoteViewModel @Inject constructor(
    private val repository: NoteRepository
) : ViewModel() {


    fun addNote(title: String, text: String) {
        repository.addNote(title, text)
    }

}