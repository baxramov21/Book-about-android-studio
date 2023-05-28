package com.example.projectkarina.data.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NotesDao {

    @Query("SELECT * FROM notes_table")
    fun getItemsList(): LiveData<List<NoteDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addItem(note: NoteDbModel)

    @Query("DELETE FROM notes_table WHERE id=:itemId")
    fun deleteItem(itemId: Int)

    @Query("SELECT * FROM notes_table WHERE id=:itemId LIMIT 1")
    fun getItem(itemId: Int): NoteDbModel
}