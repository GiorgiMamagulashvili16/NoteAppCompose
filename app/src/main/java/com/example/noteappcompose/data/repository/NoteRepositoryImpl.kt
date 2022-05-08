package com.example.noteappcompose.data.repository

import com.example.noteappcompose.data.data_source.NoteDao
import com.example.noteappcompose.domain.model.NoteDomainModel
import com.example.noteappcompose.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NoteRepositoryImpl(private val dao: NoteDao) : NoteRepository {
    override fun getAllNote(): Flow<List<NoteDomainModel>> =
        dao.getAllNote().map { it.map { model -> model.noteEntityToDomain() } }

    override suspend fun getNoteById(id: Int): NoteDomainModel? {
        return dao.getNoteById(id)?.noteEntityToDomain()
    }

    override suspend fun insertNote(note: NoteDomainModel) {
        dao.insertNote(note.noteDomainToEntity())
    }

    override suspend fun deleteNote(note: NoteDomainModel) {
        dao.deleteNote(note.noteDomainToEntity())
    }
}