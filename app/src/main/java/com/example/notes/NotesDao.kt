package com.example.notes

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NotesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note: Note)

    @Delete
    suspend fun delete(note: Note)

    //Query that returns a list of words sorted in ascending order.
    @Query("Select * from notes_table order by id ASC")
    fun getAllNotes():LiveData<List<Note>>
}

//suspend fun deleteAll(): Declares a suspend function to delete all the words.
//There is no convenience annotation for deleting multiple entities, so it's annotated with the generic @Query.
//@Query("DELETE FROM word_table")