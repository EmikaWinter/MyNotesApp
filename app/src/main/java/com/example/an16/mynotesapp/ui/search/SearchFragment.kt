package com.example.an16.mynotesapp.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.an16.mynotesapp.R
import com.example.an16.mynotesapp.databinding.FragmentSearchBinding
import com.example.an16.mynotesapp.model.Note
import com.example.an16.mynotesapp.ui.note.NoteFragment.Companion.getNoteFragment
import com.example.an16.mynotesapp.ui.search.adapter.SearchListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private var binding: FragmentSearchBinding? = null

    private val viewModel: SearchViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.listResult.observe(viewLifecycleOwner) { listResult ->
            setListNote(listResult)
        }
        binding?.searchButton?.setOnClickListener {
            viewModel.loadListResult(binding?.searchInput?.text.toString())

        }
    }

    private fun setListNote(listNote: ArrayList<Note>) {

        if (listNote.isEmpty()) {
            binding?.searchResult?.visibility = View.VISIBLE
        } else {
            binding?.searchResult?.visibility = View.INVISIBLE
        }

        binding?.searchRecyclerView?.run {
            if (adapter == null) {
                layoutManager = LinearLayoutManager(requireContext()).apply {
                    reverseLayout = true
                    stackFromEnd = true
                }

                adapter = SearchListAdapter { itemId ->
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.container, getNoteFragment(itemId))
                        .addToBackStack(null)
                        .commit()
                }
            }
            (adapter as SearchListAdapter).submitList(listNote)
        }
    }
}