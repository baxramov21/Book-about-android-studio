package com.example.projectkarina.data.db

import com.example.projectkarina.domain.entities.Note

class Mapper {
    fun mapEntityToDbModel(note: Note): NoteDbModel {
        return NoteDbModel(
            id = note.id,
            title = note.title,
            value = note.value
        )
    }

    fun mapDbModelToEntity(dbModel: NoteDbModel): Note {
        return Note(
            dbModel.id,
            dbModel.title,
            dbModel.value
        )
    }

    fun mapEntityListToDbModelList(list: List<Note>): List<NoteDbModel> {
        return list.map { mapEntityToDbModel(it) }
    }

    fun mapDbModelListToEntityList(list: List<NoteDbModel>): List<Note> {
        return list.map { mapDbModelToEntity(it) }
    }
}