package com.example.an16.mynotesapp.model

object NotesDatabase {

    var notes = ArrayList<Note>()
    var id: Int = 0

    fun add(note: Note){
        notes.add(note)
        id++
    }

    fun delete(){
//        TODO
    }

    fun edit(){
//        TODO
    }
}