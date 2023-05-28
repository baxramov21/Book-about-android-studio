package com.example.projectkarina.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.projectkarina.NotesListFragment
import com.example.projectkarina.R
import com.example.projectkarina.databinding.FragmentAddItemBinding
import com.example.projectkarina.domain.entities.Note
import com.example.projectkarina.presentation.view_model.MainViewModel
import com.example.projectkarina.presentation.view_model.ViewModelFactory

class AddItemFragment : Fragment() {

    private var param1: Note? = null

    private var _binding: FragmentAddItemBinding? = null
    private val binding: FragmentAddItemBinding
        get() = _binding ?: throw NullPointerException("FragmentAddItemBinding is null")

    private val viewModelFactory by lazy {
        ViewModelFactory(requireActivity().application)
    }

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getSerializable(ARG_PARAM1) as Note?
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddItemBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (param1 == null) {
            checkInputs()
        } else {
            editingMode()
        }

    }

    private fun editingMode() {
        param1?.let {
            binding.etNoteTitle.setText(it.title)
            binding.etNoteValue.setText(it.value)

            binding.buttonSaveItem.setOnClickListener {
                val isEditTextTitle = checkEText(binding.etNoteTitle)
                val isEditTextValue = checkEText(binding.etNoteValue)
                if (isEditTextTitle && isEditTextValue) {

                    binding.tilNoteValue.error = null
                    binding.tilNoteTitle.error = null

                    val title = getETextValue(binding.etNoteTitle)
                    val value = getETextValue(binding.etNoteValue)

                    viewModel.addNote(Note(id = id, title = title, value = value))

                    openFragment(NotesListFragment.newInstance())
                } else {
                    with(binding) {
                        tilNoteTitle.error = "Site name or url is empty"
                        tilNoteValue.error = "Site name or url is empty"
                        binding.guidelineBottom.setGuidelinePercent(GUIDELINE_PERCENT)
                    }
                }
            }
        }
    }

    private fun checkInputs() {
        binding.buttonSaveItem.setOnClickListener {
            val isEditTextTitle = checkEText(binding.etNoteTitle)
            val isEditTextValue = checkEText(binding.etNoteValue)
            if (isEditTextTitle && isEditTextValue) {

                binding.tilNoteValue.error = null
                binding.tilNoteTitle.error = null

                val title = getETextValue(binding.etNoteTitle)
                val value = getETextValue(binding.etNoteValue)

                viewModel.addNote(Note(title = title, value = value))

                openFragment(NotesListFragment.newInstance())
            } else {
                with(binding) {
                    tilNoteTitle.error = "Site name or url is empty"
                    tilNoteValue.error = "Site name or url is empty"
                    binding.guidelineBottom.setGuidelinePercent(GUIDELINE_PERCENT)
                }
            }
        }
    }

    private fun openFragment(fragment: Fragment) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, fragment)
            .commit()
    }

    private fun checkEText(editText: EditText): Boolean {
        return editText.text.isNotEmpty() && editText.text.isNotBlank()
    }

    private fun getETextValue(editText: EditText) = editText.text.toString()

    private fun showToast(message_type: Int, title: String, message: String) {
        SweetAlertDialog(context, message_type)
            .setTitleText(title)
            .setContentText(message)
            .show()
    }

    companion object {
        const val ARG_PARAM1 = "param1"
        const val GUIDELINE_PERCENT = 0.54f

        @JvmStatic
        fun newInstance(note: Note? = null) = AddItemFragment().apply {
            arguments = Bundle().apply {
                putSerializable(ARG_PARAM1, note)
            }
        }
    }
}