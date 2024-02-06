package com.example.an16.mynotesapp.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.an16.mynotesapp.model.Note
import com.example.an16.mynotesapp.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val repository: NoteRepository) : ViewModel() {

    val listResult = MutableLiveData<ArrayList<Note>>()

    fun loadListResult(key: String) {

       listResult.value = repository.searchNoteByKeyword(key)
    }


}