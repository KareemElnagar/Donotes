package com.kareem.donotes.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kareem.donotes.NotesApplication
import com.kareem.donotes.dao.NotesDao
import com.kareem.donotes.entities.Notes

@Database(
    entities = [Notes::class],
    version = 1,
    exportSchema = false
)
abstract class NotesDatabase : RoomDatabase() {

    abstract val dao: NotesDao

    companion object {
        @Volatile
        private var daoInstance: NotesDao? = null

        private fun buildDatabase(context: Context): NotesDatabase =
            Room.databaseBuilder(
                context.applicationContext,
                NotesDatabase::class.java,
                "notes_database"
            ).fallbackToDestructiveMigration()
                .build()

        fun getDaoInstance(context: Context): NotesDao {
            synchronized(this){
                if (daoInstance == null) {
                    daoInstance = buildDatabase(context).dao
                }
                return daoInstance as NotesDao
            }

        }
    }

    suspend fun getAllDataBaseItems(): List<Notes> {
        return getDaoInstance(NotesApplication.getApplicationContext()).getAllNotes()
    }


}