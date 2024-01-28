package com.example.an16.mynotesapp.ui.allnotes.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.an16.mynotesapp.R
import com.example.an16.mynotesapp.model.Note

class NoteAdapter(
    private val onClick: (note: Note?, v: View) -> Unit
) :
    RecyclerView.Adapter<NoteAdapter.NoteHolder>() {

    private var notesList: ArrayList<Note> = arrayListOf()

    inner class NoteHolder(view: View) : RecyclerView.ViewHolder(view) {

        private var note: Note? = null
        private var titleItem: TextView
        private var textItem: TextView
        private var dateItem: TextView
        private var contextMenu: ImageView

        init {
            titleItem = view.findViewById(R.id.note_title_item)
            textItem = view.findViewById(R.id.note_text_item)
            dateItem = view.findViewById(R.id.note_date_item)
            contextMenu = view.findViewById(R.id.context_menu)

            textItem.setOnClickListener {
                toggleEllipsize()
            }

            contextMenu.setOnClickListener { onClick(note,contextMenu) }
        }

        fun bind(note: Note) {
            this.note = note
            titleItem.text = note.title
            textItem.text = note.text
            dateItem.text = note.date.toString()
        }

        private fun toggleEllipsize() {
            with(textItem) {
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
        return NoteHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: NoteHolder, position: Int) {
        holder.bind(notesList[position])
    }

    override fun getItemCount(): Int {
        return notesList.size
    }

    fun setData(notesList: ArrayList<Note>){
        this.notesList = notesList
        notifyDataSetChanged()
    }
}