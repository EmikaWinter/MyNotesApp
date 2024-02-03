package com.example.an16.mynotesapp.ui.search

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.an16.mynotesapp.R
import com.example.an16.mynotesapp.databinding.FragmentSearchBinding
import com.example.an16.mynotesapp.model.Note
import com.example.an16.mynotesapp.ui.editnote.EditNoteDialogFragment
import com.example.an16.mynotesapp.ui.home.adapter.HomeListAdapter
import com.example.an16.mynotesapp.ui.search.adapter.SearchListAdapter
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private const val ID_EXTRA = "id"

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private var binding: FragmentSearchBinding? = null

    private var list: ArrayList<Note> = arrayListOf()

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

        setListNote(list)

//        binding?.searchResult?.visibility = View.INVISIBLE


        binding?.searchButton?.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                val key = binding?.searchInput?.text.toString()
                list = viewModel.getNotesByKeyword(key)

                setListNote(list)

//                if (list.isEmpty()) {
//                    binding?.searchResult?.visibility = View.VISIBLE
//                } else {
//                    setListNote(list)
//                }
            }
        }


//        viewModel.listNote.observe(viewLifecycleOwner) { listNote ->
//            setListNote(listNote)
//        }

        val dividerItemDecoration = DividerItemDecoration(requireContext(), RecyclerView.VERTICAL)
        dividerItemDecoration.setDrawable(resources.getDrawable(R.drawable.divider_item_decorator))
        binding?.searchRecyclerView?.addItemDecoration(dividerItemDecoration)

    }

    private fun setListNote(listNote: ArrayList<Note>) {
        if (list.isEmpty()) {
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

                adapter = HomeListAdapter { itemId, view ->

                    val popup = PopupMenu(requireContext(), view)
                    val inflater: MenuInflater = popup.menuInflater
                    inflater.inflate(R.menu.menu_popup, popup.menu)
                    popup.setForceShowIcon(true)

                    popup.setOnMenuItemClickListener { menuItem ->
                        when (menuItem.itemId) {
                            R.id.edit_option -> {
                                EditNoteDialogFragment().apply {
                                    arguments = bundleOf(ID_EXTRA to itemId)
//                                    onChangedItem = {
//                                        viewModel.loadListNote()
//                                    }
                                }.show(parentFragmentManager, null)
                                true
                            }

                            R.id.copy_option -> {
                                val clipboardManager =
                                    requireContext().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

                                lifecycleScope.launch {
                                    val note: Note? = viewModel.getNoteById(itemId)
                                    val clipData = ClipData.newPlainText("text", note?.text)
                                    clipboardManager.setPrimaryClip(clipData)
                                    Toast.makeText(context, R.string.copied, Toast.LENGTH_SHORT)
                                        .show()
                                }

                                true
                            }

                            R.id.delete_option -> {

                                MaterialAlertDialogBuilder(requireContext())
                                    .setTitle(R.string.delete)
                                    .setIcon(R.drawable.ic_warning)
                                    .setMessage(R.string.alert_dialog_delete)
                                    .setPositiveButton(R.string.yes) { dialog, _ ->

                                        viewModel.deleteNote(itemId)

                                        dialog.dismiss()
                                        Toast.makeText(
                                            requireContext(), R.string.deleted, Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                    .setNegativeButton(R.string.no) { dialog, _ ->
                                        dialog.dismiss()
                                    }
                                    .create()
                                    .show()

                                true
                            }

                            else -> {
                                true
                            }
                        }

                    }
                    popup.show()
                }
            }
            (adapter as? SearchListAdapter)?.submitList(listNote)
        }
    }
}
