package com.example.noteappcompose.presentation.home_screen.states

import com.example.noteappcompose.domain.model.NoteDomainModel

data class NoteState(
    val noteList: List<NoteDomainModel> = emptyList(),
    val noteOrder: NoteSortByState = NoteSortByState.Newest,
    val isOrderSectionVisible: Boolean = false
)
