package com.example.noteappcompose.data.data_source

import android.content.Context
import androidx.room.Room

fun provideNoteDatabase(context: Context):NoteDatabase =
    Room.databaseBuilder(context, NoteDatabase::class.java, "note_db").build()

fun provideNoteDao(db: NoteDatabase) = db.getNoteDao()