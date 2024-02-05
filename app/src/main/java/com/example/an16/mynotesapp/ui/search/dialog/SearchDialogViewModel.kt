package com.example.an16.mynotesapp.ui.search.dialog

import androidx.lifecycle.ViewModel
import com.example.an16.mynotesapp.model.Note
import com.example.an16.mynotesapp.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchDialogViewModel @Inject constructor(
    private val repository: NoteRepository
) : ViewModel() {


    suspend fun getNotesByKeyword(key: String): ArrayList<Note> {
        return repository.getNotesByKeyword(key)
    }
}