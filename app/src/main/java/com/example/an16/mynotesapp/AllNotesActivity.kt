package com.example.an16.mynotesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

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
    }
}