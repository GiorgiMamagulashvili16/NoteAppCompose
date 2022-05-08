package com.example.noteappcompose.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.noteappcompose.domain.model.NoteDomainModel

@Entity(tableName = "note_table")
data class NoteEntity(
    val title: String,
    val content: String,
    val timestamp: Long,
    val color: Int,
    @PrimaryKey val id: Int? = null
) {

    fun noteEntityToDomain(): NoteDomainModel {
        return NoteDomainModel(
            title = this.title,
            content = this.content,
            timeStamp = this.timestamp,
            color = color
        )
    }
}
