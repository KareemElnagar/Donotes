package com.kareem.donotes.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kareem.donotes.entities.Notes

@Dao
interface NotesDao {
    @Query("SELECT * FROM Notes ORDER BY id DESC")
    suspend fun getAllNotes(): List<Notes>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNotes(notes: Notes)

    @Delete
    suspend fun deleteNotes(notes: Notes)

    @Query("SELECT (SELECT COUNT(*) FROM Notes) == 0")
    fun isEmpty(): Boolean
}