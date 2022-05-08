package com.example.noteappcompose.presentation.home_screen.states

import com.example.noteappcompose.domain.model.NoteDomainModel

sealed class NoteEvent() {
    data class OrderNote(val noteOrder: NoteSortByState) : NoteEvent()
    data class DeleteNote(val note: NoteDomainModel) : NoteEvent()
    object ToggleOrderSection : NoteEvent()
    object RestoreNote : NoteEvent()
}
