package com.example.an16.mynotesapp.ui.home

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
import com.example.an16.mynotesapp.databinding.FragmentHomeBinding
import com.example.an16.mynotesapp.model.Note
import com.example.an16.mynotesapp.repository.SharedPreferencesRepository
import com.example.an16.mynotesapp.ui.LoginFragment
import com.example.an16.mynotesapp.ui.addnote.AddNoteFragment
import com.example.an16.mynotesapp.ui.home.adapter.HomeListAdapter
import com.example.an16.mynotesapp.ui.editnote.EditNoteDialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val ID_EXTRA = "id"

@AndroidEntryPoint
class HomeFragment : Fragment() {

    @Inject
    lateinit var sharedPreferencesRepository: SharedPreferencesRepository

    private val viewModel: HomeViewModel by viewModels()

    private var binding: FragmentHomeBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.listNote.observe(viewLifecycleOwner) { listNote ->
            setListNote(listNote)
        }

        val dividerItemDecoration = DividerItemDecoration(requireContext(), RecyclerView.VERTICAL)
        dividerItemDecoration.setDrawable(resources.getDrawable(R.drawable.divider_item_decorator))
        binding?.homeRecyclerView?.addItemDecoration(dividerItemDecoration)

        binding?.logoutTextView?.setOnClickListener {

            MaterialAlertDialogBuilder(requireContext())
                .setTitle(R.string.log_out)
                .setMessage(R.string.alert_dialog_logout)
                .setIcon(R.drawable.ic_logout)
                .setPositiveButton(R.string.yes) { dialog, _ ->

                    sharedPreferencesRepository.logout()

                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.container, LoginFragment())
                        .commit()
                    dialog.dismiss()
                }
                .setNegativeButton(R.string.no) { dialog, _ ->
                    dialog.dismiss()
                }
                .create()
                .show()
        }

        binding?.addNewTextView?.setOnClickListener {

            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.container, AddNoteFragment())
                .addToBackStack(null)
                .commit()
        }
        viewModel.loadListNote()
    }

    private fun setListNote(listNote: ArrayList<Note>) {
        binding?.homeRecyclerView?.run {
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
                                    onChangedItem = {
                                        viewModel.loadListNote()
                                    }
                                }.show(parentFragmentManager, null)
                                true
                            }

                            R.id.copy_option -> {
                                val clipboardManager =
                                    requireContext().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

                                lifecycleScope.launch(Dispatchers.IO) {
                                    val note: Note? = viewModel.getNoteById(itemId)
                                    val clipData = ClipData.newPlainText("text", note?.text)
                                    clipboardManager.setPrimaryClip(clipData)
                                }
                                Toast.makeText(context, R.string.copied, Toast.LENGTH_SHORT).show()
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
            (adapter as? HomeListAdapter)?.submitList(listNote)
        }
    }
}
