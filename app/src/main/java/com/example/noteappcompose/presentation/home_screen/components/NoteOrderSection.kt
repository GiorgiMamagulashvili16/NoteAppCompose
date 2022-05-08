package com.example.noteappcompose.presentation.home_screen.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.noteappcompose.presentation.home_screen.states.NoteSortByState

@Composable
fun NoteOrderSection(
    modifier: Modifier = Modifier,
    noteSortType: NoteSortByState = NoteSortByState.Newest,
    onOrderChange: (NoteSortByState) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            DefaultRadioButton(
                text = "Title",
                checked = noteSortType is NoteSortByState.Title,
                onSelected = { onOrderChange.invoke(NoteSortByState.Title) }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = "Newest",
                checked = noteSortType is NoteSortByState.Newest,
                onSelected = { onOrderChange.invoke(NoteSortByState.Newest) }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = "Oldest",
                checked = noteSortType is NoteSortByState.Oldest,
                onSelected = { onOrderChange.invoke(NoteSortByState.Oldest) }
            )
        }
    }
}