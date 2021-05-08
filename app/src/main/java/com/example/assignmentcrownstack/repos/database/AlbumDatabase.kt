package com.example.assignmentcrownstack.repos.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Album::class], version = 1)
abstract class AlbumDatabase : RoomDatabase() {

    abstract fun albumDao(): AlbumDao

    companion object {

        @Volatile
        private var INSTANCE: AlbumDatabase? = null

        fun getDatabase(context: Context): AlbumDatabase {
            val instance = INSTANCE
            if (instance != null) {
                return instance
            }
            synchronized(this) {
                val instance11 = Room.databaseBuilder(
                    context.applicationContext,
                    AlbumDatabase::class.java,
                    "album_database"
                ).build()
                INSTANCE = instance11
                return instance11
            }
        }

    }
}