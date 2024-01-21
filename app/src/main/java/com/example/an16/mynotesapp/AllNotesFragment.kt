package com.example.an16.mynotesapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.an16.mynotesapp.databinding.FragmentAllNotesBinding
import com.example.an16.mynotesapp.model.Note
import com.example.an16.mynotesapp.noteAdapter.NoteAdapter
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class AllNotesFragment : Fragment() {

    private var binding: FragmentAllNotesBinding? = null

    private val notesList = ArrayList<Note>()
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
            parentFragmentManager.beginTransaction()
                .replace(R.id.container, LoginFragment())
                .addToBackStack(null)
                .commit()
        }

        addNew?.setOnClickListener {
            addNote()
        }
    }

//                TODO change on fragment
        private fun addNote() {
        val inflater = LayoutInflater.from(requireContext())
        val view = inflater.inflate(R.layout.add_note_item, null)

        val title = view.findViewById<EditText>(R.id.title_edit_text)
        val text = view.findViewById<EditText>(R.id.text_edit_text)

        val validator = Validator()

        val builder = MaterialAlertDialogBuilder(requireContext())

        builder.setView(view)
            .setPositiveButton(R.string.ok)
            { dialog, _ ->
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
                    Toast.makeText(requireContext(), R.string.saved, Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(requireContext(), R.string.empty_note, Toast.LENGTH_LONG).show()
                }
            }
            .setNegativeButton(R.string.cancel)
            { dialog, _ ->
                dialog.dismiss()
                Toast.makeText(requireContext(), R.string.cancel, Toast.LENGTH_SHORT).show()
            }
            .create()
            .show()
    }

}



