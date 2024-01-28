package com.example.an16.mynotesapp.model

object NotesDatabase {

    var notes = ArrayList<Note>()
    var id: Int = 0

    fun add(note: Note){
        notes.add(note)
        id++
    }

    fun delete(note: Note){
        notes.remove(note)
    }

    fun edit(){
//        TODO
    }
}