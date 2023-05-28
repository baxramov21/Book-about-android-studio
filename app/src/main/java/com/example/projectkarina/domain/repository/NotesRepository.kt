package com.example.projectkarina.domain.repository

import androidx.lifecycle.LiveData
import com.example.projectkarina.domain.entities.Note

interface NotesRepository {

    suspend fun addNote(note: Note)

    suspend fun getNotesList(): LiveData<List<Note>>

    suspend fun deleteNote(itemId: Int)

    suspend fun getNote(itemId: Int): Note
}