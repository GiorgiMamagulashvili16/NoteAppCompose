package com.example.noteappcompose.domain.use_case

import com.example.noteappcompose.domain.model.NoteDomainModel
import com.example.noteappcompose.domain.repository.NoteRepository

class InsertNoteUseCase(private val repository: NoteRepository) {
    suspend fun execute(note: NoteDomainModel) {
        repository.insertNote(note)
    }
}