package com.example.an16.mynotesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.an16.mynotesapp.model.Note
import com.example.an16.mynotesapp.model.NotesDatabase
import java.util.Calendar
import java.util.Date

class AddNoteActivity : AppCompatActivity() {

    private var notesList = NotesDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        val titleEdit = findViewById<EditText>(R.id.title_edit_text)
        val textEdit = findViewById<EditText>(R.id.text_edit_text)
        val currentDate: Date = Calendar.getInstance().time

        val validator = Validator()

        findViewById<TextView>(R.id.back_textView).setOnClickListener {
            startActivity(Intent(this, AllNotesActivity::class.java))
        }

        findViewById<Button>(R.id.add_button).setOnClickListener {

            val titleText = titleEdit.text.toString()
            val messageText = textEdit.text.toString()

            if (validator.validateText(titleText) && validator.validateText(messageText)) {

                notesList.add(
                    Note(
                        notesList.id,
                        titleText,
                        messageText,
                        currentDate.toString()
                    )
                )
                Toast.makeText(this, R.string.saved, Toast.LENGTH_LONG).show()

                startActivity(Intent(this, AllNotesActivity::class.java))

            } else {
                Toast.makeText(this, R.string.empty_note, Toast.LENGTH_LONG).show()
            }
        }
    }
}