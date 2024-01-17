package com.example.an16.mynotesapp

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.an16.mynotesapp.model.NotesDatabase
import com.example.an16.mynotesapp.noteAdapter.NoteListAdapter

class AllNotesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_notes)

        findViewById<TextView>(R.id.logout_textView).setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        findViewById<TextView>(R.id.add_new_textView).setOnClickListener {
            startActivity(Intent(this, AddNoteActivity::class.java))
        }


        val recyclerView = findViewById<RecyclerView>(R.id.all_notes_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val adapter = NoteListAdapter { id ->

            Toast.makeText(
                this, findViewById<TextView>(R.id.note_text_item).text,
                Toast.LENGTH_LONG
            ).show()

            startActivity(
                Intent(this, NoteActivity::class.java).apply {
                    putExtra(ID, id)
                })
        }

        recyclerView.adapter = adapter
        adapter.submitList(NotesDatabase.notes)
    }
}