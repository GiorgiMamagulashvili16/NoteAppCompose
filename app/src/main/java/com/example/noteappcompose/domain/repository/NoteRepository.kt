package com.example.noteappcompose.domain.repository

import com.example.noteappcompose.domain.model.NoteDomainModel
import kotlinx.coroutines.flow.Flow

interface NoteRepository {

    fun getAllNote(): Flow<List<NoteDomainModel>>

    suspend fun getNoteById(id: Int): NoteDomainModel?

    suspend fun insertNote(note: NoteDomainModel)

    suspend fun deleteNote(note: NoteDomainModel)
}