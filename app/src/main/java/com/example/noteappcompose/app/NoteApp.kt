package com.example.noteappcompose.app

import android.app.Application
import com.example.noteappcompose.di.dbModule
import com.example.noteappcompose.di.repoModule
import com.example.noteappcompose.di.useCaseModule
import com.example.noteappcompose.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class NoteApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@NoteApp)
            modules(listOf(dbModule,repoModule, useCaseModule, viewModelModule))
        }
    }
}