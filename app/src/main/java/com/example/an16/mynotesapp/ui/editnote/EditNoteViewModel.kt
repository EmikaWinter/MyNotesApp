package com.example.an16.mynotesapp.ui.editnote

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
class EditNoteViewModel @Inject constructor(
    private val repository: NoteRepository
) : ViewModel() {

    val note = MutableLiveData<Note>()

    var onChangedItem: (() -> Unit)? = null

    fun getNoteById(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            note.postValue(repository.getNoteById(id))
        }
    }

    fun editNote(title: String, text: String) {
        viewModelScope.launch(Dispatchers.IO) {
            note.value?.copy(title = title, text = text)?.let {
                repository.updateNote(it)
            }
            onChangedItem?.invoke()
        }
    }
}