package com.example.notes

import androidx.lifecycle.LiveData

class NoteRepository(private val notedao:NotesDao) {

    val allNotes:LiveData<List<Note>> = notedao.getAllNotes()

    suspend fun insert(note:Note){
        notedao.insert(note)
    }

    suspend fun delete(note:Note){
        notedao.delete(note)
    }

}