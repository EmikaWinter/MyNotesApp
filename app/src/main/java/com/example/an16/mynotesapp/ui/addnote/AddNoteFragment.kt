package com.example.an16.mynotesapp.ui.addnote

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.an16.mynotesapp.R
import com.example.an16.mynotesapp.Validator
import com.example.an16.mynotesapp.databinding.FragmentAddNoteBinding
import com.example.an16.mynotesapp.ui.MainFragment
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

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
        val currentDate: Date = Date()
        val formatDate: String =
            SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(currentDate)

        val validator = Validator()

        binding?.backTextView?.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment())
                .commit()
        }

        binding?.addButton?.setOnClickListener {

            val titleText = titleEdit?.text.toString()
            val messageText = textEdit?.text.toString()

            if (validator.validateText(titleText) && validator.validateText(messageText)) {

                viewModel.addNote(titleText, messageText)

                Toast.makeText(requireContext(), R.string.saved, Toast.LENGTH_LONG).show()

                parentFragmentManager.beginTransaction()
                    .replace(R.id.container, MainFragment())
                    .commit()
            } else {
                Toast.makeText(requireContext(), R.string.empty_note, Toast.LENGTH_LONG).show()
            }
        }
    }
}
