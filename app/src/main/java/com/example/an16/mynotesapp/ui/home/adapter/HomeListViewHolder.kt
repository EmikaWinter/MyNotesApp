package com.example.an16.mynotesapp.ui.home.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.an16.mynotesapp.databinding.NoteItemBinding
import com.example.an16.mynotesapp.model.Note
import java.util.Date

class HomeListViewHolder(private val binding: NoteItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(
        note: Note,
        onClick: (id: Int, view: View) -> Unit
    ) {
        binding.noteTitleItem.text = note.title
        binding.noteTextItem.text = note.text
        note.date = Date()
        binding.menu.setOnClickListener {
            onClick(note.id, binding.menu)
        }
    }
}