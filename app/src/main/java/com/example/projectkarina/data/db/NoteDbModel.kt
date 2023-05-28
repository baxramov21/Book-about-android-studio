package com.example.projectkarina.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes_table")
data class NoteDbModel(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    val title: String,
    val value: String
)