package com.example.projectkarina.data.repository_implementations

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.projectkarina.data.db.Mapper
import com.example.projectkarina.data.db.NotesDatabase
import com.example.projectkarina.domain.entities.Note
import com.example.projectkarina.domain.repository.NotesRepository

class NotesRepositoryImpl(application: Application) : NotesRepository {

    private val db = NotesDatabase.getInstance(application).getDao()
    private val mapper = Mapper()


    override suspend fun addNote(note: Note) {
        db.addItem(
            mapper.mapEntityToDbModel(note)
        )
    }

    override suspend fun getNotesList(): LiveData<List<Note>> =
        MediatorLiveData<List<Note>>().apply {
            addSource(db.getItemsList()) {
                value = mapper.mapDbModelListToEntityList(it)
            }
        }

    override suspend fun deleteNote(itemId: Int) {
        db.deleteItem(itemId)
    }

    override suspend fun getNote(itemId: Int): Note {
        return mapper.mapDbModelToEntity(db.getItem(itemId))
    }

}