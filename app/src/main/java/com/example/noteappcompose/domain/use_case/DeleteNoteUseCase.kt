package com.example.noteappcompose.domain.use_case

import com.example.noteappcompose.domain.model.NoteDomainModel
import com.example.noteappcompose.domain.repository.NoteRepository

class DeleteNoteUseCase(private val noteRepository: NoteRepository) {

    suspend fun execute(note: NoteDomainModel) {
        noteRepository.deleteNote(note)
    }
}