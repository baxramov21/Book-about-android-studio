package com.example.projectkarina.domain.repository

import com.example.projectkarina.domain.entities.Note

interface NotesRepository {

    fun addNote(note: Note)

    fun getNotes(): List<Note>
}