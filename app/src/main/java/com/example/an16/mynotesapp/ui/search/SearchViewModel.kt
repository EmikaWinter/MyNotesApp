package com.example.an16.mynotesapp.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.an16.mynotesapp.model.Note
import com.example.an16.mynotesapp.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val repository: NoteRepository) : ViewModel() {

    val listResult = MutableLiveData<ArrayList<Note>>()

    fun loadListResult(key: String) {
        viewModelScope.launch(Dispatchers.IO) {
            listResult.postValue(repository.searchNoteByKeyword(key))
        }
    }

    fun deleteNote(id: Int, key: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteNote(id)
            loadListResult(key)
        }
    }

    suspend fun getNoteById(id: Int): Note? {
        return repository.getNoteById(id)
    }


}