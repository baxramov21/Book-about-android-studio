package com.example.projectkarina.presentation.rc_view

import androidx.recyclerview.widget.DiffUtil
import com.example.projectkarina.domain.entities.Note

class NoteItemDiffCallback : DiffUtil.ItemCallback<Note>() {

    override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem == newItem
    }

}
