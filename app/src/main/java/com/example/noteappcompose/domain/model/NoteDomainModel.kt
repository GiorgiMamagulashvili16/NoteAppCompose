package com.example.noteappcompose.domain.model

import com.example.noteappcompose.data.model.NoteEntity
import com.example.noteappcompose.presentation.ui.theme.*


data class NoteDomainModel(
    val id: Int? = null,
    val title: String,
    val content: String,
    val timeStamp: Long,
    val color: Int
) {
    companion object {
        val noteColorList = listOf(RedOrange, LightGreen, Violet, BabyBlue, RedPink)
    }
    fun noteDomainToEntity(): NoteEntity {
        return NoteEntity(
            id = this.id,
            title = this.title,
            content = this.content,
            timestamp = this.timeStamp,
            color = this.color
        )
    }
}
