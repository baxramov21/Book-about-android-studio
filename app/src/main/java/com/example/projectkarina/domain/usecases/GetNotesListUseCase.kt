package com.example.projectkarina.domain.usecases

import androidx.lifecycle.LiveData
import com.example.projectkarina.domain.entities.Note
import com.example.projectkarina.domain.repository.NotesRepository

class GetNotesListUseCase(private val repository: NotesRepository) {
    suspend fun getNotesList(): LiveData<List<Note>> = repository.getNotesList()
}