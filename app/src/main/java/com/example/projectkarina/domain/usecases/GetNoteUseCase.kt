package com.example.projectkarina.domain.usecases

import com.example.projectkarina.domain.entities.Note
import com.example.projectkarina.domain.repository.NotesRepository

class GetNoteUseCase(private val repository: NotesRepository) {
    suspend fun getNote(itemId: Int): Note {
        return repository.getNote(itemId)
    }
}