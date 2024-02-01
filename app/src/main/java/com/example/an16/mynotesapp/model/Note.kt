package com.example.an16.mynotesapp.model

import java.util.Date

data class Note(
    val id: Int,
    var title: String,
    var text: String,
    var date: Date
)