package com.example.an16.mynotesapp.ui.allnotes

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.an16.mynotesapp.R
import com.example.an16.mynotesapp.databinding.FragmentAllNotesBinding
import com.example.an16.mynotesapp.model.Note
import com.example.an16.mynotesapp.repository.SharedPreferencesRepository
import com.example.an16.mynotesapp.ui.LoginFragment
import com.example.an16.mynotesapp.ui.addnote.AddNoteFragment
import com.example.an16.mynotesapp.ui.allnotes.adapter.NoteAdapter
import com.example.an16.mynotesapp.ui.editnote.EditNoteDialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder

private const val ID_EXTRA = "id"
private const val TITLE_EXTRA = "title"
private const val TEXT_EXTRA = "text"
private const val EDIT_NOTE_EXTRA = "editNote"

class AllNotesFragment : Fragment() {

    private val viewModel: AllNotesViewModel by viewModels()

    private var binding: FragmentAllNotesBinding? = null

    private lateinit var noteAdapter: NoteAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAllNotesBinding.inflate(layoutInflater)
        return binding?.root
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.listNote.observe(viewLifecycleOwner) { listNote ->
            setListNote(listNote)

        }
//        viewModel.getListNote()

//        val recyclerView = binding?.allNotesRecyclerView

//        val dividerItemDecoration = DividerItemDecoration(requireContext(), RecyclerView.VERTICAL)
//        dividerItemDecoration.setDrawable(resources.getDrawable(R.drawable.divider_item_decorator))
//        recyclerView?.addItemDecoration(dividerItemDecoration)



        binding?.logoutTextView?.setOnClickListener {

            MaterialAlertDialogBuilder(requireContext())
                .setTitle(R.string.log_out)
                .setMessage(R.string.alert_dialog_logout)
                .setIcon(R.drawable.ic_logout)
                .setPositiveButton(R.string.yes) { dialog, _ ->

                    SharedPreferencesRepository.logout()

                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.container, LoginFragment())
                        .commit()
                    dialog.dismiss()
                }
                .setNegativeButton(R.string.no) { dialog, _ ->
                    dialog.dismiss()
                }
                .create()
                .show()
        }

        binding?.addNewTextView?.setOnClickListener {

            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.container, AddNoteFragment())
                .addToBackStack(null)
                .commit()
        }
//        viewModel.loadListNote()
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    private fun setListNote(listNote: ArrayList<Note>) {
        binding?.allNotesRecyclerView?.run {
            if (noteAdapter == null) {
                layoutManager = LinearLayoutManager(requireContext()).apply {
                    reverseLayout = true
                    stackFromEnd = true


                    noteAdapter = NoteAdapter { note, v ->

                        popupMenus(v, note)
                    }

                }
            }
        }
        (noteAdapter as? NoteAdapter)?.setData(listNote)
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    private fun popupMenus(view: View, note: Note?) {
        note ?: return
        val popupMenus = PopupMenu(context, view)

        popupMenus.inflate(R.menu.menu_popup)
        popupMenus.setForceShowIcon(true)

        popupMenus.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.edit_option -> {

//                    parentFragmentManager.setFragmentResultListener(
//                        EDIT_NOTE_EXTRA,
//                        viewLifecycleOwner
//                    ) { _, bundle ->
//                        note.title = bundle.getString(TITLE_EXTRA).toString()
//                        note.text = bundle.getString(TEXT_EXTRA).toString()
//                        NotesDatabase.update(note)
//                        noteAdapter.setData(viewModel.loadListNote())
//                    }

                    EditNoteDialogFragment().apply {
                        arguments = bundleOf(
                            ID_EXTRA to viewModel.note.value?.id
                        )
                    }.show(parentFragmentManager, null)

                    true
                }

                R.id.copy_option -> {

                    val clipboardManager =
                        requireContext().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                    val clipData = ClipData.newPlainText("text", note.text)
                    clipboardManager.setPrimaryClip(clipData)
                    Toast.makeText(context, R.string.copied, Toast.LENGTH_SHORT).show()
                    true
                }

                R.id.delete_option -> {

                    MaterialAlertDialogBuilder(requireContext())
                        .setTitle(R.string.delete)
                        .setIcon(R.drawable.ic_warning)
                        .setMessage(R.string.alert_dialog_delete)
                        .setPositiveButton(R.string.yes) { dialog, _ ->
//                            NotesDatabase.delete(note)
                            viewModel.deleteNote()

//                            noteAdapter.setData(viewModel.loadListNote())
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
    }

}