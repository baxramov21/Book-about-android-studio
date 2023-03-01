package com.example.projectkarina.domain.usecases

import com.example.projectkarina.domain.entities.Note
import com.example.projectkarina.domain.repository.NotesRepository

class GetNotesUseCase(private val repository: NotesRepository) {
    operator fun invoke(): List<Note> = repository.getNotes()
}