package com.example.projectkarina.presentation.rc_view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.projectkarina.R
import com.example.projectkarina.domain.entities.Note

private const val ARG_PARAM1 = "param1"

class ItemInfoFragment : Fragment() {
    private var param1: Note? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getSerializable(ARG_PARAM1) as Note?
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_item_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tvNoteName = requireActivity().findViewById<TextView>(R.id.tv_noteName)
        val tvNoteData = requireActivity().findViewById<TextView>(R.id.tv_noteValue)

        param1?.let {
            tvNoteName.text = it.title
            tvNoteData.text = it.value
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(note: Note) =
            ItemInfoFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAM1, note)
                }
            }
    }
}


