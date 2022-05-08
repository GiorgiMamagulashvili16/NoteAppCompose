package com.example.noteappcompose.presentation.home_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteappcompose.domain.model.NoteDomainModel
import com.example.noteappcompose.domain.use_case.DeleteNoteUseCase
import com.example.noteappcompose.domain.use_case.GetNotesUseCase
import com.example.noteappcompose.domain.use_case.InsertNoteUseCase
import com.example.noteappcompose.presentation.home_screen.states.NoteEvent
import com.example.noteappcompose.presentation.home_screen.states.NoteSortByState
import com.example.noteappcompose.presentation.home_screen.states.NoteState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class NotesViewModel(
    private val insertNoteUseCase: InsertNoteUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase,
    private val getNotesUseCase: GetNotesUseCase
) : ViewModel() {


    private val _state = mutableStateOf(NoteState())
    val state: State<NoteState> = _state

    private var recentlyDeletedNote: NoteDomainModel? = null

    private var getNoteJob: Job? = null

    fun onEvent(noteEvent: NoteEvent) {
        when (noteEvent) {
            is NoteEvent.OrderNote -> {
                if (state.value.noteOrder::class == noteEvent.noteOrder::class
                    && state.value.noteOrder == noteEvent.noteOrder
                ) {
                    return
                }
                getNote(noteEvent.noteOrder)
            }
            is NoteEvent.DeleteNote -> {
                deleteNote(noteEvent.note)
                recentlyDeletedNote = noteEvent.note
            }
            is NoteEvent.ToggleOrderSection -> {
                _state.value = state.value.copy(
                    isOrderSectionVisible = state.value.isOrderSectionVisible.not()
                )
            }
            is NoteEvent.RestoreNote -> {
                restoreNote()
            }
        }
    }

    private fun getNote(noteSortByState: NoteSortByState) = viewModelScope.launch(Dispatchers.IO) {
        getNoteJob?.cancel()
        getNoteJob = getNotesUseCase.execute(noteSortByState).onEach { notes ->
            _state.value = state.value.copy(
                noteList = notes,
                noteOrder = noteSortByState
            )
        }.launchIn(viewModelScope)
    }

    private fun deleteNote(note: NoteDomainModel) = viewModelScope.launch(Dispatchers.IO) {
        deleteNoteUseCase.execute(note)
    }

    private fun restoreNote() = viewModelScope.launch(Dispatchers.IO) {
        insertNoteUseCase.execute(recentlyDeletedNote ?: return@launch)
        recentlyDeletedNote = null
    }
}