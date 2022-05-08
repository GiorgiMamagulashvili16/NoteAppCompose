package com.example.noteappcompose.di

import com.example.noteappcompose.data.data_source.provideNoteDao
import com.example.noteappcompose.data.data_source.provideNoteDatabase
import com.example.noteappcompose.data.repository.NoteRepositoryImpl
import com.example.noteappcompose.domain.repository.NoteRepository
import com.example.noteappcompose.domain.use_case.DeleteNoteUseCase
import com.example.noteappcompose.domain.use_case.GetNotesUseCase
import com.example.noteappcompose.domain.use_case.InsertNoteUseCase
import com.example.noteappcompose.presentation.home_screen.NotesViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dbModule = module {
    single { provideNoteDatabase(androidContext()) }
    single { provideNoteDao(get()) }
}
val repoModule = module {
    factory<NoteRepository> { NoteRepositoryImpl(get()) }
}
val useCaseModule = module {
    single { DeleteNoteUseCase(get()) }
    single { GetNotesUseCase(get()) }
    single { InsertNoteUseCase(get()) }
}
val viewModelModule = module {
    viewModel {
        NotesViewModel(get(), get(), get())
    }
}