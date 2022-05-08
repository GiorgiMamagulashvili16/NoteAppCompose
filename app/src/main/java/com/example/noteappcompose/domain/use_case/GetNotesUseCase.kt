package com.example.noteappcompose.domain.use_case

import com.example.noteappcompose.domain.model.NoteDomainModel
import com.example.noteappcompose.domain.repository.NoteRepository
import com.example.noteappcompose.presentation.home_screen.states.NoteSortByState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetNotesUseCase(private val noteRepository: NoteRepository) {

    fun execute(noteSortByState: NoteSortByState): Flow<List<NoteDomainModel>> {
        return noteRepository.getAllNote().map { notes ->
            when (noteSortByState) {
                is NoteSortByState.Newest -> {
                    notes.sortedByDescending { it.timeStamp }
                }
                is NoteSortByState.Oldest -> {
                    notes.sortedBy { it.timeStamp }

                }
                is NoteSortByState.Title -> {
                    notes.sortedBy { it.title.lowercase() }

                }
            }
        }

    }
}