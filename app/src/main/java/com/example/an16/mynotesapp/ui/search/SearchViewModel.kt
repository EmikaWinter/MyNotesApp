package com.example.an16.mynotesapp.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.an16.mynotesapp.model.Note
import com.example.an16.mynotesapp.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val repository: NoteRepository) : ViewModel() {

    val listNote = MutableLiveData<ArrayList<Note>>()

    private var job: Job? = null


    fun loadListNote() {
        job?.cancelChildren()
        job = viewModelScope.launch(Dispatchers.IO) {
            listNote.postValue(repository.getNoteList())
        }
    }
    suspend fun getNoteById(id: Int): Note? {
        return repository.getNoteById(id)
    }

    fun deleteNote(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteNote(id)
            loadListNote()
        }

    }
    suspend fun getNotesByKeyword(key: String): ArrayList<Note> {
        return repository.getNotesByKeyword(key)
    }
}