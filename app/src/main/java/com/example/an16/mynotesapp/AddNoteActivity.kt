package com.example.an16.mynotesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class AddNoteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        findViewById<TextView>(R.id.back_textView).setOnClickListener {
            startActivity(Intent(this, AllNotesActivity::class.java))
        }

        findViewById<Button>(R.id.add_button).setOnClickListener {
            TODO()
        }
    }
}