package com.example.projectkarina.domain.usecases

import com.example.projectkarina.domain.repository.NotesRepository

class DeleteNoteUseCase(private var repository: NotesRepository) {
    suspend fun deleteNote(itemId: Int) {
        repository.deleteNote(itemId)
    }
}