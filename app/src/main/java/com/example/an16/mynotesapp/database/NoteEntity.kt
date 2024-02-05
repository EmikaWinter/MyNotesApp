package com.example.an16.mynotesapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "NoteEntity")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo("title")
    var title: String,
    @ColumnInfo("text")
    var text: String,
    @ColumnInfo("date")
    var date: Date,
    @ColumnInfo("userId")
    var userId: Int
)