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

    fun getListNote(): ArrayList<Note> {
        return repository.getNoteList()
    }

    fun loadListResult(key: String) {
        val list = repository.getNoteList()
       listResult.value = (list.filter { note ->
            note.title.contains(key, true)
            note.text.contains(key, true)
        } as ArrayList<Note>)
    }

}