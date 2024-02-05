package com.example.an16.mynotesapp.ui.search.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.an16.mynotesapp.databinding.DialogSearchBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchDialogFragment : BottomSheetDialogFragment() {

    var onFound: (() -> Unit)? = null
    private var binding: DialogSearchBinding? = null

    private val viewModel: SearchDialogViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogSearchBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val key = binding?.searchInput?.text.toString()

        lifecycleScope.launch(Dispatchers.IO) {
            viewModel.getNotesByKeyword(key)
            onFound?.invoke()
        }
    }
}