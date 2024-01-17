package com.example.an16.mynotesapp

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.an16.mynotesapp.model.Note
import com.example.an16.mynotesapp.noteAdapter.NoteAdapter
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class ViewNotesActivity : AppCompatActivity() {

    private val notesList = ArrayList<Note>()
    private lateinit var noteAdapter: NoteAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_notes)

        val addBtn = findViewById<TextView>(R.id.add_new_textView)

        val recyclerView = findViewById<RecyclerView>(R.id.all_notes_recycler_view)

        val dividerItemDecoration = DividerItemDecoration(this, RecyclerView.VERTICAL)
        dividerItemDecoration.setDrawable(resources.getDrawable(R.drawable.divider_item_decorator))
        recyclerView.addItemDecoration(dividerItemDecoration)

        noteAdapter = NoteAdapter(this, notesList)

        recyclerView.layoutManager = LinearLayoutManager(this).apply {
            reverseLayout = true
            stackFromEnd = true
        }

        recyclerView.adapter = noteAdapter

        addBtn.setOnClickListener {
            addNote()
        }
    }

        private fun addNote() {
        val inflater = LayoutInflater.from(this)
        val view = inflater.inflate(R.layout.add_note_item, null)

        val title = view.findViewById<EditText>(R.id.title_edit_text)
        val text = view.findViewById<EditText>(R.id.text_edit_text)

        val validator = Validator()

        val builder = MaterialAlertDialogBuilder(this)

        builder.setView(view)
            .setPositiveButton(R.string.ok)
            { dialog, _ ->
//                TODO keep alert dialog open when is not valid
                val titleText = title.text.toString()
                val textText = text.text.toString()
                val date: String = SimpleDateFormat(
                    "dd/MM/yyyy",
                    Locale.getDefault()
                ).format(Calendar.getInstance().time)

                if (validator.validateText(titleText) && validator.validateText(textText)) {
                    notesList.add(Note(titleText, textText, date))
                    noteAdapter.notifyDataSetChanged()
                    dialog.dismiss()
                    Toast.makeText(this, R.string.saved, Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, R.string.empty_note, Toast.LENGTH_LONG).show()
                }
            }
            .setNegativeButton(R.string.cancel)
            { dialog, _ ->
                dialog.dismiss()
                Toast.makeText(this, R.string.cancel, Toast.LENGTH_SHORT).show()
            }
            .create()
            .show()
    }
}
