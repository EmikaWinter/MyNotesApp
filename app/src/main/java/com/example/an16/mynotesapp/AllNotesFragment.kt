package com.example.an16.mynotesapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.an16.mynotesapp.databinding.FragmentAllNotesBinding
import com.example.an16.mynotesapp.model.NotesDatabase
import com.example.an16.mynotesapp.noteAdapter.NoteAdapter
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class AllNotesFragment : Fragment() {

    private var binding: FragmentAllNotesBinding? = null

    private val notesList = NotesDatabase.notes
    private lateinit var noteAdapter: NoteAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAllNotesBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val addNew = binding?.addNewTextView
        val logout = binding?.logoutTextView

        val recyclerView = binding?.allNotesRecyclerView

        val dividerItemDecoration = DividerItemDecoration(requireContext(), RecyclerView.VERTICAL)
        dividerItemDecoration.setDrawable(resources.getDrawable(R.drawable.divider_item_decorator))
        recyclerView?.addItemDecoration(dividerItemDecoration)

        noteAdapter = NoteAdapter(requireContext(), notesList)

        recyclerView?.layoutManager = LinearLayoutManager(requireContext()).apply {
            reverseLayout = true
            stackFromEnd = true
        }

        recyclerView?.adapter = noteAdapter

        logout?.setOnClickListener {

            MaterialAlertDialogBuilder(requireContext())
                .setTitle(R.string.log_out)
                .setMessage(R.string.alert_dialog_logout)
                .setIcon(R.drawable.ic_logout)
                .setPositiveButton(R.string.yes) { dialog, _ ->
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.container, LoginFragment())
                        .commit()
                    dialog.dismiss()
                }
                .setNegativeButton(R.string.no){dialog, _ ->
                    dialog.dismiss()
                }
                .create()
                .show()
        }

        addNew?.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.container, AddNoteFragment())
                .addToBackStack(null)
                .commit()
        }
        noteAdapter.notifyDataSetChanged()
    }
}



