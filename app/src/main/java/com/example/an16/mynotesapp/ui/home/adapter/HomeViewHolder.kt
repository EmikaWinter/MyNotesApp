package com.example.an16.mynotesapp.ui.home.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.an16.mynotesapp.databinding.NoteItemBinding
import com.example.an16.mynotesapp.model.Note
import com.example.an16.mynotesapp.util.getSimpleDate

class HomeViewHolder(private val binding: NoteItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(
        note: Note,
        onClick: (id: Int, view: View) -> Unit
    ) {
        binding.noteTitleItem.text = note.title
        binding.noteTextItem.text = note.text
        binding.noteDateItem.text = note.date.getSimpleDate()

        binding.noteTextItem.setOnClickListener {
            toggleEllipsize()
        }

        binding.menu.setOnClickListener {
            onClick(note.id, binding.menu)
        }
    }

    private fun toggleEllipsize() {
        with(binding.noteTextItem) {
            if (maxLines == 2) {
                maxLines = Integer.MAX_VALUE
                ellipsize = null
            } else {
                maxLines = 2
                ellipsize = android.text.TextUtils.TruncateAt.END
            }
        }
    }
}