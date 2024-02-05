package com.example.an16.mynotesapp.ui.editnote

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.an16.mynotesapp.model.Note
import com.example.an16.mynotesapp.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EditNoteViewModel @Inject constructor(
    private val repository: NoteRepository
) : ViewModel() {

    val note = MutableLiveData<Note>()

    fun getNoteById(id: Int) {
        note.value = repository.getNoteById(id)
    }

    fun editNote(title: String, text: String) {
        note.value?.copy(title = title, text = text)?.let {
            repository.updateNote(it)
        }
    }
}