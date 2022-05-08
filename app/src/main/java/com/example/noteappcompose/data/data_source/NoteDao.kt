package com.example.noteappcompose.data.data_source

import androidx.room.*
import com.example.noteappcompose.data.model.NoteEntity
import kotlinx.coroutines.flow.Flow
@Dao
interface NoteDao {

    @Query("SELECT * FROM note_table")
    fun getAllNote(): Flow<List<NoteEntity>>

    @Query("SELECT * FROM note_table WHERE id = :id")
    fun getNoteById(id: Int): NoteEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: NoteEntity)

    @Delete
    suspend fun deleteNote(noteEntity: NoteEntity)
}