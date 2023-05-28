package com.example.projectkarina

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projectkarina.databinding.FragmentNotesListBinding
import com.example.projectkarina.domain.entities.Note
import com.example.projectkarina.presentation.AddItemFragment
import com.example.projectkarina.presentation.rc_view.ItemInfoFragment
import com.example.projectkarina.presentation.rc_view.NoteAdapter
import com.example.projectkarina.presentation.view_model.MainViewModel
import com.example.projectkarina.presentation.view_model.ViewModelFactory
import com.google.android.material.snackbar.Snackbar

class NotesListFragment : Fragment() {

    private var _binding: FragmentNotesListBinding? = null
    private val binding: FragmentNotesListBinding
        get() = _binding ?: throw NullPointerException("FragmentNotesListBinding is null")

    private val viewModelFactory by lazy {
        ViewModelFactory(requireActivity().application)
    }

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
    }

    private val noteAdapter = NoteAdapter()
    private val itemsList = ArrayList<Note>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNotesListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        binding.floatingActionButtonAddNewNote.setOnClickListener {
            openFragment(AddItemFragment.newInstance())
        }
    }

    private fun initRecyclerView() {
        with(binding) {
            rcViewNotes.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

            rcViewNotes.adapter = noteAdapter

            observeLiveData(viewModel.notesList) {
                noteAdapter.submitList(it)
                itemsList.addAll(it)
            }
            initClickListeners()
            deleteItems()
        }
    }

    private fun initClickListeners() {
        noteAdapter.onShopItemLongClickListener = {
            val bundle = Bundle().apply {
                putSerializable(AddItemFragment.ARG_PARAM1, it)
            }

            openFragment(AddItemFragment.newInstance(it))
        }

        noteAdapter.onShopItemClickListener = {
            openFragment(ItemInfoFragment.newInstance(it))
        }
    }

    private fun deleteItems() {
        ItemTouchHelper(object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val deletedItem = itemsList[viewHolder.adapterPosition]
                val position = viewHolder.adapterPosition
                itemsList.removeAt(viewHolder.adapterPosition)

                viewModel.deleteNote(deletedItem.id)
                Snackbar.make(
                    binding.rcViewNotes,
                    "Deleted " + deletedItem.title,
                    Snackbar.LENGTH_LONG
                )
                    .setAction(
                        "Undo"
                    ) {
                        itemsList.add(position, deletedItem)
                        viewModel.addNote(deletedItem)
                    }.show()
            }
        }).attachToRecyclerView(binding.rcViewNotes)

    }

    private fun openFragment(fragment: Fragment) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun <T> observeLiveData(
        liveData: LiveData<List<T>>,
        operation: (list: List<T>) -> Unit
    ) {
        liveData.observe(viewLifecycleOwner) {
            operation(it)
        }
    }

    override fun onDestroy() {
        if (_binding != null) {
            _binding = null
        }
        super.onDestroy()
    }


    companion object {
        @JvmStatic
        fun newInstance() = NotesListFragment()
    }
}