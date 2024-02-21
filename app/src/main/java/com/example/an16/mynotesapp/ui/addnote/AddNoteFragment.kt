package com.example.an16.mynotesapp.ui.addnote

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.an16.mynotesapp.R
import com.example.an16.mynotesapp.databinding.FragmentAddNoteBinding
import com.example.an16.mynotesapp.util.Validator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddNoteFragment : Fragment() {

    private var binding: FragmentAddNoteBinding? = null

    private val viewModel: AddNoteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddNoteBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val titleEdit = binding?.titleEditText
        val textEdit = binding?.textEditText

        val validator = Validator()

        binding?.backTextView?.setOnClickListener {
            findNavController().navigate(R.id.action_addNoteFragment_to_home)
        }

        binding?.addButton?.setOnClickListener {

            val titleText = titleEdit?.text.toString()
            val messageText = textEdit?.text.toString()

            if (validator.validateText(titleText) && validator.validateText(messageText)) {

                viewModel.addNote(titleText, messageText)

                Toast.makeText(requireContext(), R.string.saved, Toast.LENGTH_LONG).show()

                findNavController().navigate(R.id.action_addNoteFragment_to_home)
            } else {
                Toast.makeText(requireContext(), R.string.empty_note, Toast.LENGTH_LONG).show()
            }
        }
    }
}
