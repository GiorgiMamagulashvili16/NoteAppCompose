package com.example.noteappcompose.presentation.home_screen

import androidx.compose.animation.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Sort
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.noteappcompose.presentation.home_screen.components.NoteItem
import com.example.noteappcompose.presentation.home_screen.components.NoteOrderSection
import com.example.noteappcompose.presentation.home_screen.states.NoteEvent
import kotlinx.coroutines.launch
import org.koin.androidx.compose.viewModel

@Composable
fun NotesScreen() {
    val notesViewModel by viewModel<NotesViewModel>()
    val scaffoldState = rememberScaffoldState()
    val screenState = notesViewModel.state.value
    val scope = rememberCoroutineScope()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {

                },
                backgroundColor = MaterialTheme.colors.primary,

                ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "add button")
            }
        },
        scaffoldState = scaffoldState
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
        ) {
            ToolBarSection(modifier = Modifier.fillMaxWidth(), viewModel = notesViewModel)
            AnimatedVisibility(
                visible = screenState.isOrderSectionVisible,
                enter = fadeIn() + slideInVertically(),
                exit = fadeOut() + slideOutVertically()
            ) {
                NoteOrderSection(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    noteSortType = screenState.noteOrder,
                    onOrderChange = {
                        notesViewModel.onEvent(NoteEvent.OrderNote(it))
                    }
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(screenState.noteList) { note ->
                    NoteItem(note = note, modifier = Modifier
                        .fillMaxWidth()
                        .clickable { },
                        onDeleteAction = {
                            notesViewModel.onEvent(NoteEvent.DeleteNote(note))
                            scope.launch {
                                val snackBarAction = scaffoldState.snackbarHostState.showSnackbar(
                                    message = "Note Deleted",
                                    actionLabel = "Undo",
                                )
                                if (snackBarAction == SnackbarResult.ActionPerformed) {
                                    notesViewModel.onEvent(NoteEvent.RestoreNote)
                                }
                            }
                        })
                }
            }
            Spacer(modifier = Modifier.height(5.dp))
        }
    }
}

@Composable
fun ToolBarSection(
    modifier: Modifier = Modifier,
    viewModel: NotesViewModel
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "My Notes", style = MaterialTheme.typography.h4)
        IconButton(onClick = { viewModel.onEvent(NoteEvent.ToggleOrderSection) }) {
            Icon(imageVector = Icons.Default.Sort, contentDescription = "sort_icon")
        }
    }
}