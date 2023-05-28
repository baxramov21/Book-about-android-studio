package com.example.projectkarina.data.db

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [NoteDbModel::class], version = 1, exportSchema = false)
abstract class NotesDatabase() : RoomDatabase() {

    abstract fun getDao(): NotesDao

    companion object {

        private var INSTANCE: NotesDatabase? = null
        private val LOCK = Any()
        private const val DB_NAME = "MAIN_DATABASE"

        fun getInstance(application: Application): NotesDatabase {
            INSTANCE?.let {
                return it
            }

            synchronized(LOCK) {
                INSTANCE?.let {
                    return it
                }

                val db = Room.databaseBuilder(
                    application,
                    NotesDatabase::class.java,
                    DB_NAME
                ).allowMainThreadQueries()
                    .build()

                INSTANCE = db
                return db
            }
        }
    }
}