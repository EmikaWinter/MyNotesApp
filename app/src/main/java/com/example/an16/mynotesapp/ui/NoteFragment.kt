package com.example.an16.mynotesapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.an16.mynotesapp.databinding.NoteFragmentBinding

private const val ID_EXTRA = "id"

class NoteFragment : Fragment() {
    private var binding: NoteFragmentBinding? = null

    private val viewModel: NoteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = NoteFragmentBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.note.observe(viewLifecycleOwner) { note ->
            binding?.noteTitleItem?.text = note.title
            binding?.noteTextItem?.text = note.text
        }
        viewModel.getItemById(arguments?.getInt(ID_EXTRA, 0) ?: 0)
    }
}