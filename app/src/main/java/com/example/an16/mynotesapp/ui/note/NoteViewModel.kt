package com.example.an16.mynotesapp.ui.note

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.an16.mynotesapp.model.Note
import com.example.an16.mynotesapp.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(private val repository: NoteRepository) : ViewModel() {

    val note = MutableLiveData<Note>()

    fun getNoteById(id: Int) {
        repository.getNoteById(id)?.let {
            note.value = it
        }
    }
}