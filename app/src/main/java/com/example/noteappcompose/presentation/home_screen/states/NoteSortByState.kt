package com.example.noteappcompose.presentation.home_screen.states

sealed class NoteSortByState {
    object Title : NoteSortByState()
    object Newest : NoteSortByState()
    object Oldest : NoteSortByState()
}
