package com.example.noteappcompose.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.noteappcompose.data.model.NoteEntity

@Database(entities = [NoteEntity::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun getNoteDao(): NoteDao
}