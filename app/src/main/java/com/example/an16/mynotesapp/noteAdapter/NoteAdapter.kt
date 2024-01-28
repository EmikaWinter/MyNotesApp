package com.example.an16.mynotesapp.noteAdapter

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.an16.mynotesapp.R
import com.example.an16.mynotesapp.model.Note
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class NoteAdapter(val context: Context, val notesList: ArrayList<Note>) :
    RecyclerView.Adapter<NoteAdapter.NoteHolder>() {

    inner class NoteHolder(view: View) : RecyclerView.ViewHolder(view) {

        var titleItem: TextView
        var textItem: TextView
        var dateItem: TextView
        private var contextMenu: ImageView

        init {
            titleItem = view.findViewById(R.id.note_title_item)
            textItem = view.findViewById(R.id.note_text_item)
            dateItem = view.findViewById(R.id.note_date_item)
            contextMenu = view.findViewById(R.id.context_menu)

            textItem.setOnClickListener {
                toggleEllipsize()
            }

            contextMenu.setOnClickListener { popupMenus(it) }
        }

        private fun popupMenus(view: View) {
            val popupMenus = PopupMenu(context, view)
            val position = notesList[adapterPosition]
            popupMenus.inflate(R.menu.context_menu)
            popupMenus.setOnMenuItemClickListener {menuItem->
                when (menuItem.itemId) {
                    R.id.edit_option -> {

//                        val id = AllNotesFragment().arguments?.getInt("id", 1)
//                        val note: Note? = NotesDatabase.notes.find { it.id == id }
//
//                        EditNoteDialogFragment().apply {
//                            arguments = bundleOf("title" to note?.title)
//                            arguments = bundleOf("text" to note?.text)
//                        }.show(AllNotesFragment().childFragmentManager, null)
//                        TODO correct editText with display previous text
                        val viewItem =
                            LayoutInflater.from(context).inflate(R.layout.dialog_edit_note, null)
                        val title = viewItem.findViewById<EditText>(R.id.title_edit_text)
                        val text = viewItem.findViewById<EditText>(R.id.text_edit_text)

                        MaterialAlertDialogBuilder(context)
                            .setView(viewItem)
                            .setPositiveButton(R.string.ok) { dialog, _ ->
                                position.title = title.text.toString()
                                position.text = text.text.toString()
                                notifyDataSetChanged()
                                Toast.makeText(context, R.string.edited, Toast.LENGTH_SHORT).show()
                                dialog.dismiss()
                            }
                            .setNegativeButton(R.string.cancel) { dialog, _ ->
                                dialog.dismiss()
                            }
                            .create()
                            .show()

                        true
                    }

                    R.id.copy_option -> {

                        val clipboardManager =
                            context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                        val clipData = ClipData.newPlainText("text", textItem.text.toString())
                        clipboardManager.setPrimaryClip(clipData)
                        Toast.makeText(context, R.string.copied, Toast.LENGTH_SHORT).show()
                        true
                    }

                    R.id.delete_option -> {

                        MaterialAlertDialogBuilder(context)
                            .setTitle(R.string.delete)
                            .setIcon(R.drawable.ic_warning)
                            .setMessage(R.string.alert_dialog_delete)
                            .setPositiveButton(R.string.yes) { dialog, _ ->
                                notesList.removeAt(adapterPosition)
                                notifyDataSetChanged()
                                dialog.dismiss()
                                Toast.makeText(context, R.string.deleted, Toast.LENGTH_SHORT).show()
                            }
                            .setNegativeButton(R.string.no) { dialog, _ ->
                                dialog.dismiss()
                            }
                            .create()
                            .show()

                        true
                    }

                    else -> true
                }
            }
            popupMenus.show()

            val popup = PopupMenu::class.java.getDeclaredField("mPopup")
            popup.isAccessible = true
            val menu = popup.get(popupMenus)
            menu.javaClass.getDeclaredMethod("setForceShowIcon", Boolean::class.java)
                .invoke(menu, true)
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
        val newList = notesList[position]
        holder.titleItem.text = newList.title
        holder.textItem.text = newList.text
        holder.dateItem.text = newList.date
    }

    override fun getItemCount(): Int {
        return notesList.size
    }
}