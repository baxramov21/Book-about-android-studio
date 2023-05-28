package com.example.projectkarina.presentation.view_model

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectkarina.data.repository_implementations.NotesRepositoryImpl
import com.example.projectkarina.domain.entities.Note
import com.example.projectkarina.domain.usecases.AddNoteUseCase
import com.example.projectkarina.domain.usecases.DeleteNoteUseCase
import com.example.projectkarina.domain.usecases.GetNoteUseCase
import com.example.projectkarina.domain.usecases.GetNotesListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : ViewModel() {

    private val coroutine = Dispatchers.Main

    private val repository = NotesRepositoryImpl(application)

    private val getNotesListUseCase = GetNotesListUseCase(repository)
    private val getNoteUseCase = GetNoteUseCase(repository)
    private val addNoteUseCase = AddNoteUseCase(repository)
    private val deleteNoteUseCase = DeleteNoteUseCase(repository)

//    private val _notesList = MutableLiveData<List<Note>>()
//    val notesList: LiveData<List<Note>>
//        get() = _notesList

//    init {
//        if (notesList.value?.isEmpty() == true) {
//            viewModelScope.launch {
//                getNotesListUseCase.
//                _notesList.value = getNotesListUseCase.getNotesList().value
//            }
//        }
//    }

    lateinit var notesList: LiveData<List<Note>>

    init {
        viewModelScope.launch {
            notesList = getNotesListUseCase.getNotesList()
        }
    }

    fun getNote(itemID: Int): Note? {
        var result: Note? = null
        viewModelScope.launch {
            result = getNoteUseCase.getNote(itemID)
        }
        return result
    }

    fun addNote(note: Note) {
        viewModelScope.launch {
            addNoteUseCase.addNote(note)
        }
    }

    fun deleteNote(itemId: Int) {
        viewModelScope.launch {
            deleteNoteUseCase.deleteNote(itemId)
        }
    }

}