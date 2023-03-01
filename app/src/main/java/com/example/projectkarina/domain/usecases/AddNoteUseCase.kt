package com.example.projectkarina.domain.usecases

import android.provider.ContactsContract
import com.example.projectkarina.domain.entities.Note
import com.example.projectkarina.domain.repository.NotesRepository

class AddNoteUseCase(private val repository: NotesRepository) {
    operator fun invoke(note: Note) {
        repository.addNote(note)
    }
}