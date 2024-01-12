package com.example.an16.mynotesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.an16.mynotesapp.model.NotesDatabase

const val ID = "id"

class NoteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)

        findViewById<TextView>(R.id.back_textView).setOnClickListener {
            startActivity(Intent(this, AllNotesActivity::class.java))
        }

        val id: Int = intent.getIntExtra(ID, 0)

        NotesDatabase.notes.find { id == it.id }?.let { note ->
            findViewById<TextView>(R.id.note_title_item).text = note.title
            findViewById<TextView>(R.id.note_text_item).text = note.text
            findViewById<TextView>(R.id.note_date_item).text = note.date
        }
    }
}