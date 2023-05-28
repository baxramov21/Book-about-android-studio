package com.example.projectkarina.domain.usecases

import com.example.projectkarina.domain.entities.Note
import com.example.projectkarina.domain.repository.NotesRepository

class AddNoteUseCase(private val repository: NotesRepository) {
    suspend  fun addNote(note: Note) {
        repository.addNote(note)
    }
}