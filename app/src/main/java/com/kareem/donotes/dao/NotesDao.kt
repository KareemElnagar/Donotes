package com.kareem.donotes.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.kareem.donotes.entities.Notes

@Dao
interface NotesDao {
    @Query("SELECT * FROM Notes ORDER BY id DESC")
    suspend fun getAllNotes(): List<Notes>
   @Query("SELECT * FROM Notes WHERE id=:id")
    suspend fun getSpecifiedNote(id:Int): Notes

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNotes(notes: Notes)

    @Delete
    suspend fun deleteNotes(notes: Notes)
    @Query("DELETE FROM Notes WHERE id=:id")
    suspend fun deleteSpecifiedNote(id:Int)

    @Update
    suspend fun updateNotes(notes: Notes)



    @Query("SELECT (SELECT COUNT(*) FROM Notes) == 0")
    fun isEmpty(): Boolean
}