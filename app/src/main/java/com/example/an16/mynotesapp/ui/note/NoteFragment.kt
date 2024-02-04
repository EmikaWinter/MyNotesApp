package com.example.an16.mynotesapp.ui.note

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.an16.mynotesapp.databinding.FragmentNoteBinding
import com.example.an16.mynotesapp.util.getSimpleDate
import dagger.hilt.android.AndroidEntryPoint

private const val ID_EXTRA = "id"

@AndroidEntryPoint
class NoteFragment : Fragment() {

    private var binding: FragmentNoteBinding? = null

    private val viewModel: NoteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNoteBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.note.observe(viewLifecycleOwner) { note ->
            binding?.noteTitleItem?.text = note.title
            binding?.noteTextItem?.text = note.text
            binding?.noteDateItem?.text = note.date.getSimpleDate()

        }

        viewModel.getNoteById(arguments?.getInt(ID_EXTRA, 1) ?: 1)

    }

    companion object {
        fun getNoteFragment(id: Int): NoteFragment {
            return NoteFragment().apply {
                arguments = bundleOf(ID_EXTRA to id)
            }
        }
    }
}