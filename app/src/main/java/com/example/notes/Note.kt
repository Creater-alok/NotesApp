package com.example.notes

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//Each @Entity class represents a SQLite table.Annotate your class declaration to indicate that it's an entity.
@Entity(tableName = "notes_table")
data class Note(
    @ColumnInfo(name = "text") val text: String,
    @ColumnInfo(name = "date") val date: String,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
)
