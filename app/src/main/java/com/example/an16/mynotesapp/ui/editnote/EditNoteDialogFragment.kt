package com.example.an16.mynotesapp.ui.editnote

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.example.an16.mynotesapp.R
import com.example.an16.mynotesapp.util.Validator
import com.example.an16.mynotesapp.databinding.DialogEditNoteBinding
import dagger.hilt.android.AndroidEntryPoint

private const val ID_EXTRA = "id"

@AndroidEntryPoint
class EditNoteDialogFragment : DialogFragment() {

    var onChangedItem: (() -> Unit)? = null

    private var binding: DialogEditNoteBinding? = null

    private val viewModel: EditNoteViewModel by viewModels()

    private val validator: Validator = Validator()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogEditNoteBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.note.observe(viewLifecycleOwner) {
            binding?.titleEditText?.setText(it.title)
            binding?.textEditText?.setText(it.text)
        }

        viewModel.getNoteById(arguments?.getInt(ID_EXTRA) ?: 0)

        binding?.titleEditText?.run {
            doAfterTextChanged {
                error = if (it.toString().isBlank()) {
                    getString(R.string.name_empty_field)
                } else {
                    null
                }
            }
        }

        binding?.textEditText?.run {
            doAfterTextChanged {
                error = if (it.toString().isBlank()) {
                    getString(R.string.name_empty_field)
                } else {
                    null
                }
            }
        }

        binding?.editButton?.setOnClickListener {

            val title = binding?.titleEditText?.text.toString()
            val text = binding?.textEditText?.text.toString()

            if (validator.validateText(title) && validator.validateText(text)) {
                viewModel.editNote(title, text)
                onChangedItem?.invoke()
                dismiss()
            } else {
                Toast.makeText(requireContext(), R.string.empty_note, Toast.LENGTH_LONG).show()
            }
        }

    }

    override fun onStart() {
        super.onStart()
        val dialog = dialog
        if (dialog != null) {
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.WRAP_CONTENT
            dialog.window!!.setLayout(width, height)
        }
    }
}