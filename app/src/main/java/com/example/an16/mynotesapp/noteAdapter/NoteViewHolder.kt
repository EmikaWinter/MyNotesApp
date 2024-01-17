package com.example.an16.mynotesapp.noteAdapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.an16.mynotesapp.model.Note
import com.example.an16.mynotesapp.R

class NoteViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun bind(
        note: Note,
        onClick: (id: Int) -> Unit
    ) {
        view.findViewById<TextView>(R.id.note_title_item).run {
            text = note.title
            setOnClickListener {
                onClick(note.id)
            }
        }

        view.findViewById<TextView>(R.id.note_text_item).run {
            text = note.text
            setOnClickListener {
                onClick(note.id)
            }
        }

        view.findViewById<TextView>(R.id.note_date_item).text = note.date
    }
}