package com.example.an16.mynotesapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.DialogFragment
import com.example.an16.mynotesapp.databinding.DialogEditNoteBinding

class EditNoteDialogFragment : DialogFragment() {

    private var binding: DialogEditNoteBinding? = null

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


        binding?.titleEditText?.run {
            setText(arguments?.getString("title"))
            doAfterTextChanged {
                error = if (it.toString().isBlank()) {
                    "error"
                } else {
                    null
                }
            }
        }

        binding?.textEditText?.run {
            setText(arguments?.getString("title"))
            doAfterTextChanged {
                error = if (it.toString().isBlank()) {
                    "error"
                } else {
                    null
                }
            }
        }

    }
}